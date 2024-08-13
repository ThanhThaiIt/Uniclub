package com.Tsoft.UniClub.service.imp;

import com.Tsoft.UniClub.dto.RoleDTO;
import com.Tsoft.UniClub.entity.RoleEntity;
import com.Tsoft.UniClub.entity.UserEntity;
import com.Tsoft.UniClub.repository.UserRepository;
import com.Tsoft.UniClub.request.AuthenRequest;
import com.Tsoft.UniClub.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenServiceImp implements AuthenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<RoleDTO> checkLogin(AuthenRequest authenRequest) {


        List<RoleDTO> roles = new ArrayList<>();
        UserEntity user = userRepository.findUserEntitiesByEmail(authenRequest.email());
        if (user != null && passwordEncoder.matches(authenRequest.password(), user.getPassword())) {
            RoleEntity roleEntity = user.getRole();
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(roleEntity.getId());

            roleDTO.setName(roleEntity.getName());
            roles.add(roleDTO);
        }
        return roles;
    }
}

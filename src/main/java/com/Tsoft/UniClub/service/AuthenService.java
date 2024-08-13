package com.Tsoft.UniClub.service;

import com.Tsoft.UniClub.dto.RoleDTO;
import com.Tsoft.UniClub.entity.RoleEntity;
import com.Tsoft.UniClub.request.AuthenRequest;

import java.util.List;

public interface AuthenService {

    List<RoleDTO> checkLogin(AuthenRequest authenRequest);
}

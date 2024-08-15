package com.Tsoft.UniClub.security;

import com.Tsoft.UniClub.dto.RoleDTO;
import com.Tsoft.UniClub.entity.RoleEntity;
import com.Tsoft.UniClub.request.AuthenRequest;
import com.Tsoft.UniClub.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenProvider implements AuthenticationProvider {



    @Autowired
    private AuthenService authenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AuthenRequest authenRequest = new AuthenRequest(username, password);

        List<RoleDTO> roles = authenService.checkLogin(authenRequest);

        if (roles != null && roles.size() > 0) {
                // StreamAPI
                //map(): cho phép biến đổi kiểu dữ liệu trong quá trình duyệt mảng/ đối tượng

//            List<GrantedAuthority> grantedAuthoritieList = new ArrayList<>();
//            roles.forEach(role -> {
//                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//                grantedAuthoritieList.add(grantedAuthority);
//            });

        List<SimpleGrantedAuthority> grantedAuthoritieList  =    roles.stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList();

            return new UsernamePasswordAuthenticationToken("", "", grantedAuthoritieList);
        }else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

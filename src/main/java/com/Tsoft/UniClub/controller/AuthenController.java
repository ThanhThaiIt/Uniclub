package com.Tsoft.UniClub.controller;

import com.Tsoft.UniClub.request.AuthenRequest;
import com.Tsoft.UniClub.response.BaseResponse;
import com.Tsoft.UniClub.utils.JwtUtilHeplers;
import com.Tsoft.UniClub.service.AuthenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authen")

@CrossOrigin
public class AuthenController {


    @Autowired
    private AuthenService authenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilHeplers jwtUtilHeplers;

    private ObjectMapper objectMapper = new ObjectMapper();// chuyen sang string json

    @PostMapping
    public ResponseEntity<?> authen(@Valid @RequestBody AuthenRequest authenRequest) throws JsonProcessingException {


        //boolean isSuccess = authenService.checkLogin(authenRequest);
        BaseResponse response = new BaseResponse();


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenRequest.email(), authenRequest.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //String data = isSuccess ? jwtUtilHeplers.generateToken(authenRequest.email()) : "";

        List<GrantedAuthority> listRole = (List<GrantedAuthority>) authenticate.getAuthorities();
        String data = objectMapper.writeValueAsString(listRole);
        System.out.println("data: " + data);

        String token = jwtUtilHeplers.generateToken(data);

        response.setData(token);


        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

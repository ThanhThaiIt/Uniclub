package com.Tsoft.UniClub.filter;

import com.Tsoft.UniClub.dto.AuthorityDTO;
import com.Tsoft.UniClub.utils.JwtUtilHeplers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {
    //OncePerRequestFilter : lắng nghe tất cả các link
    @Autowired
    private JwtUtilHeplers jwtUtilHeplers;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorHeader = request.getHeader("Authorization");
        if (authorHeader != null && authorHeader.startsWith("Bearer ")) {
            String token = authorHeader.substring(7);
            String data = jwtUtilHeplers.decodeToken(token);
            if (data != null) {


                List<AuthorityDTO> authorityDTOList = objectMapper.readValue(data, new TypeReference<List<AuthorityDTO>>() {});

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                authorityDTOList.forEach(dataDTO -> {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(dataDTO.getAuthority());
                    grantedAuthorities.add(simpleGrantedAuthority);
                });

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", "", grantedAuthorities);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authenticationToken);
            }

            System.out.println("kiem tra "+ data);
        }

        filterChain.doFilter(request, response);
    }
}

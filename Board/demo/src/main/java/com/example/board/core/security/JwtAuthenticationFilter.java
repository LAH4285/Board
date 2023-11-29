package com.example.board.core.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.board.core.StringArrayConverter;
import com.example.board.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String prefixJwt = request.getHeader(JwtTokenProvider.HEADER);

        if(prefixJwt == null) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = prefixJwt.replace(JwtTokenProvider.TOKEN_PREFIX, "");

        try {
            log.debug("토근 있음.");

            DecodedJWT decodedJWT = JwtTokenProvider.verify(jwt);

            int id = decodedJWT.getClaim("id").asInt();
            String roles = decodedJWT.getClaim("roles").asString();

            StringArrayConverter stringArrayConverter = new StringArrayConverter();
            List<String> rolesList = stringArrayConverter.convertToEntityAttribute(roles);

            User user = User.builder().id(id).roles(rolesList).build();
            CustomUserDetails customUserDetails = new CustomUserDetails(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    customUserDetails,
                    customUserDetails.getPassword(),
                    customUserDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("인증 객체 생성");
        }
        catch (SignatureVerificationException sve) {
            log.debug("토큰 검증 실패");
        }
        catch (TokenExpiredException tee) {
            log.debug("토큰 사용 만료");
        } finally {
            chain.doFilter(request, response);
        }
    }
}

























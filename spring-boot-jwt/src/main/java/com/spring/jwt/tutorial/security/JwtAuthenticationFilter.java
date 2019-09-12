package com.spring.jwt.tutorial.security;

import com.spring.jwt.tutorial.config.JwtSecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.jwt.tutorial.config.JwtSecurityConstants.ROLE;
import static com.spring.jwt.tutorial.config.JwtSecurityConstants.TYPE;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private long expirationTime;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, long expirationTime) {
        this.authenticationManager = authenticationManager;
        this.expirationTime = expirationTime;
        setFilterProcessesUrl(JwtSecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        User user = ((User) auth.getPrincipal());

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = JwtSecurityConstants.JWT_SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam(TYPE, JwtSecurityConstants.TOKEN_TYPE)
                .setIssuer(JwtSecurityConstants.TOKEN_ISSUER)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim(ROLE, roles)
                .compact();

        res.addHeader(JwtSecurityConstants.TOKEN_HEADER, JwtSecurityConstants.TOKEN_PREFIX + token);

    }
}
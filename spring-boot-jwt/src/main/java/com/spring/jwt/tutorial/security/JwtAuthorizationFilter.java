package com.spring.jwt.tutorial.security;

import com.spring.jwt.tutorial.config.JwtSecurityConstants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.jwt.tutorial.config.JwtSecurityConstants.ROLE;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtSecurityConstants.TOKEN_HEADER);
        if (token == null || token.isEmpty() || !token.startsWith(JwtSecurityConstants.TOKEN_PREFIX)) {
            return null;
        }

        try {
            byte[] signingKey = JwtSecurityConstants.JWT_SECRET.getBytes();

            Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token.replace("Bearer ", ""));

            String username = parsedToken
                    .getBody()
                    .getSubject();
            if (username == null || username.isEmpty()) {
                return null;
            }

            List<GrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                    .get(ROLE)).stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority))
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, null, authorities);

        } catch (ExpiredJwtException exception) {
            logger.warn("Expired JWT token : {} cause : {}", token, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            logger.warn("Unsupported JWT token : {} cause : {}", token, exception.getMessage());
        } catch (MalformedJwtException exception) {
            logger.warn("Malformed JWT token : {} cause : {}", token, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            logger.warn("Empty or null JWT : {} cause : {}", token, exception.getMessage());
        }
        return null;
    }
}
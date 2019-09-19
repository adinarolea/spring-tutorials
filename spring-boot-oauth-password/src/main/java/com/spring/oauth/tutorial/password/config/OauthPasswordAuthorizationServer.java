package com.spring.oauth.tutorial.password.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OauthPasswordAuthorizationServer extends AuthorizationServerConfigurerAdapter {

	public static final String CLIENT = "client";
	public static final String SECRET = "secret";
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints

				.tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
				.authenticationManager(authenticationManager)
				.accessTokenConverter(jwtAccessTokenConverter);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
				.inMemory()
				.withClient(CLIENT)
				.secret(passwordEncoder.encode(SECRET))
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read", "write");
	}

}
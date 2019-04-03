package me.schoewe.b2crestapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2Config {
	
//	@Autowired(required = false)
//	ClientHttpRequestFactory clientHttpRequestFactory;
//
//	private ClientHttpRequestFactory getClientHttpRequestFactory() {
//		if (clientHttpRequestFactory == null) {
//			clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
//		}
//		return clientHttpRequestFactory;
//	}
	
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails details) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(details);
//		template.setErrorHandler(new RestTemplateResponseErrorHandler());
		return template;
	}
	
//	@Bean
//	public AccessTokenProvider clientAccessTokenProvider() {
//		ClientCredentialsAccessTokenProvider accessTokenProvider = new ClientCredentialsAccessTokenProvider();
//		accessTokenProvider.setRequestFactory(getClientHttpRequestFactory());
//		return accessTokenProvider;
//	}
}

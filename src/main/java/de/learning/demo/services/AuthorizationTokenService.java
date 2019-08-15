package de.learning.demo.services;

import de.learning.demo.applicationContext.ApplicationContextProvider;
import de.learning.demo.model.AuthorizationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "microsoft.token")
public class AuthorizationTokenService {
    @Value("${url}")
    private String url;
    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${grant_type}")
    private String grantType;
    @Value("${scope}")
    private String scope;


    public String generateToken() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(url);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType(grantType);
        resourceDetails.setScope(asList(scope));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));

        return "Bearer " + restTemplate.getAccessToken();
    }

    public static void setAuthTokenBeanValue() {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        AuthorizationToken authorizationToken = context.getBean("authorizationToken", AuthorizationToken.class);
        AuthorizationTokenService authorizationTokenService = context.getBean("authorizationTokenService", AuthorizationTokenService.class);
        authorizationToken.setTokenValue(authorizationTokenService.generateToken());
    }
}

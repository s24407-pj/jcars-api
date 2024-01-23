package edu.pjatk.jcarsapi.journey;

import edu.pjatk.jcarsapi.AbstractTestContainers;
import edu.pjatk.jcarsapi.model.Enums.ERoles;
import edu.pjatk.jcarsapi.model.request.LoginRequest;
import edu.pjatk.jcarsapi.model.request.SignupRequest;
import edu.pjatk.jcarsapi.model.response.LoginResponse;
import edu.pjatk.jcarsapi.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthenticationIT extends AbstractTestContainers {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private JwtUtil jwtUtil;


    @Test
    void canLogin() {
        //create registration customerRegistrationRequest
        SignupRequest customerRegistrationRequest = new SignupRequest(
                "Asdfg",
                "Gfdsa",
                "asdfg@wp.pl",
                "password",
                "123456789",
                "hgfhfhfhf",
                true,
                ERoles.ROLE_USER
        );

        var authenticationRequest = new LoginRequest(
                customerRegistrationRequest.getEmail(),
                customerRegistrationRequest.getPassword()
        );

        webTestClient.post()
                .uri("api/auth/signin")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authenticationRequest), LoginRequest.class)
                .exchange()
                .expectStatus()
                .isUnauthorized();

        //send a post customerRegistrationRequest
        webTestClient.post()
                .uri("api/auth/signup")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customerRegistrationRequest), SignupRequest.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        EntityExchangeResult<LoginResponse> result = webTestClient.post()
                .uri("api/auth/signin")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authenticationRequest), LoginRequest.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(new ParameterizedTypeReference<LoginResponse>() {
                })
                .returnResult();

        var responseBody = result.getResponseBody();

        assertThat(jwtUtil.validateJwtToken(responseBody.token())).isTrue();
        assertThat(responseBody.email()).isEqualTo(customerRegistrationRequest.getEmail());

    }
}

package edu.pjatk.jcarsapi.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
public class DrivingLicenseService {

    private final WebClient webClient;

    public DrivingLicenseService(@Value("${url.driving-license-gov}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<Boolean> checkDrivingLicense(String hash) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("hashDanychWyszukiwania", hash)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, // replace method reference with lambda
                        clientResponse -> Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Bad Request"
                        )))
                .bodyToMono(String.class)
                .map(response -> {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject
                            .getJSONObject("dokumentPotwierdzajacyUprawnienia")
                            .getJSONObject("stanDokumentu")
                            .getJSONObject("stanDokumentu")
                            .get("wartosc")
                            .toString();
                    return value.equals("Wydany");
                });
    }
}


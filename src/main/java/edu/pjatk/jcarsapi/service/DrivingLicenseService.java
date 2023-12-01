package edu.pjatk.jcarsapi.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
//TODO wyjatki
@Service
public class DrivingLicenseService {
    private final WebClient webClient;

    public DrivingLicenseService(WebClient.Builder webClientBuilder) {
        String url = "https://moj.gov.pl/nforms/api/UprawnieniaKierowcow/2.0.5/data/driver-permissions";
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    public Boolean checkDrivingLicense(String hash) {
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("hashDanychWyszukiwania", hash)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        String jsonString = response.block();
        if (jsonString == null) {
            System.out.println("Response is null");
            return false;
        }

        JSONObject json = new JSONObject(jsonString);
        String value = json
                .getJSONObject("dokumentPotwierdzajacyUprawnienia")
                .getJSONObject("stanDokumentu")
                .getJSONObject("stanDokumentu")
                .get("wartosc")
                .toString();

        return value.equals("Wydany");

    }
}


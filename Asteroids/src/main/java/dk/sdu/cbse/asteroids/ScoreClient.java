package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.dto.Score;
import dk.sdu.cbse.common.services.IScoreService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ScoreClient implements IScoreService {
    private final RestTemplate restTemplate;


    public ScoreClient() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setMessageConverters(List.of(
                new MappingJackson2HttpMessageConverter(
                        new com.fasterxml.jackson.databind.ObjectMapper())));
    }

    @Override
    public void givePoints(int points) {
        try {
            restTemplate.getForObject("http://localhost:8080/score?pointsToAdd=" + points, String.class);
        } catch (Exception e) {
            System.out.println("Failed to send points: " + e);
        }
    }

    @Override
    public int getScore() {
        try {
            Score response =  restTemplate.getForObject("http://localhost:8080/score/latest", Score.class);
            return response.score();
        } catch (Exception e) {
            System.out.println("Failed to get score: " + e);
            return -1;
        }
    }
}

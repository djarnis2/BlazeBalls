package dk.sdu.cbse.scoringsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ScoringSystemApplication {

    private static int score = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystemApplication.class, args);
    }

    @GetMapping("/score")
    public int calculateScore(@RequestParam(value= "pointsToAdd") int pointsToAdd) {
        score += pointsToAdd;
        return score;
    }

    @GetMapping("/score/latest")
    public Score returnScore() {
        return new Score(score);
    }
}

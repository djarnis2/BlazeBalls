package dk.sdu.cbse.scoringsystem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Score(int score) {
}

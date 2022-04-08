package br.edu.infnet.votingapi.application.controller.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetCandidateControllerTest {
    @Autowired
    private CandidateController candidateController;

    @Test
    @DisplayName("Deve retornar uma lista de candidatos")
    void getCandidates() throws Exception {
        ResponseEntity<List<Candidate>> response = candidateController.getCandidates();
        assertEquals(response.getStatusCode().value(), 200);
        for (Candidate candidate : Objects.requireNonNull(response.getBody())) {
            assertNotNull(candidate.getUuid());
            assertNotNull(candidate.getName());
            assertNotNull(candidate.getPoliticalParty());
        }
    }
}

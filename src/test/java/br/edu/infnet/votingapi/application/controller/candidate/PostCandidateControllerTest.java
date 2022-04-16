package br.edu.infnet.votingapi.application.controller.candidate;

import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PostCandidateControllerTest {
    @Autowired
    private CandidateController candidateController;

    @Test
    @DisplayName("Deve criar um candidato")
    void createCandidate() {
        final CandidateRequest candidateRequest = CandidateRequest.builder().
                name("Leonardo")
                .politicalParty(PoliticalParty.PSB)
                .build();
        ResponseEntity<Candidate> response = candidateController.createCandidate(candidateRequest);
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(Objects.requireNonNull(response.getBody()).getName(), candidateRequest.getName());
        assertEquals(response.getBody().getPoliticalParty(), candidateRequest.getPoliticalParty());
    }

    @Test
    @DisplayName("Deve lançar uma Exception ao criar um candidato")
    void createCandidateThrows() {
        final CandidateRequest candidateRequest = CandidateRequest.builder().name("Leonardo").build();
        assertThrows(ConstraintViolationException.class, () -> candidateController.createCandidate(candidateRequest));

    }
}

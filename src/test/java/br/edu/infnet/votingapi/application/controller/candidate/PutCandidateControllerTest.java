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
class PutCandidateControllerTest {
    @Autowired
    private CandidateController candidateController;
    final String uuid = "8cd8ed93-e428-493c-b56b-d0b13e51695c";

    @Test
    @DisplayName("Deve atualizar um candidato")
    void changeCandidate() {
        final CandidateRequest candidateRequest = CandidateRequest.builder().name("Fulano").politicalParty(PoliticalParty.PSB).build();
        ResponseEntity<Candidate> response = candidateController.changeCandidate(candidateRequest, uuid);
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(Objects.requireNonNull(response.getBody()).getName(), candidateRequest.getName());
        assertEquals(response.getBody().getPoliticalParty(), candidateRequest.getPoliticalParty());
    }

    @Test
    @DisplayName("Deve lançar uma Exception ao alterar um candidato")
    void changeCandidateThrows() {
        final CandidateRequest candidateRequest = CandidateRequest.builder().name("Leonardo").build();
        assertThrows(ConstraintViolationException.class, () -> candidateController.changeCandidate(candidateRequest, uuid));

    }
}

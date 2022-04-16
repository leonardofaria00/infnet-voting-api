package br.edu.infnet.votingapi.application.controller.vote;

import br.edu.infnet.votingapi.application.controller.candidate.CandidateController;
import br.edu.infnet.votingapi.application.data.candidate.CandidateRequest;
import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.data.model.candidate.PoliticalParty;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PostVoteControllerTest {
    @Autowired
    private VoteController voteController;

    @Autowired
    private CandidateController candidateController;

    @Test
    @DisplayName("Testa sucesso para votação de um candidato")
    void success() {
        final VoteRequest voteRequest = VoteRequest.builder().uuid("8cd8ed93-e428-493c-b56b-d0b13e51695c").build();
        final ResponseEntity<Vote> response = voteController.poll(voteRequest);
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(requireNonNull(response.getBody()).getUuid(), voteRequest.getUuid());
        assertEquals(response.getBody().getName(), "Fulano");
        assertEquals(response.getBody().getPoliticalParty(), PoliticalParty.PSB);
    }

    @Test
    @DisplayName("Adiciona novo voto ao candidato")
    void addNewVoteWithSuccess() {
        ResponseEntity<Candidate> candidate = createNewCandidate();
        final VoteRequest voteRequest = VoteRequest.builder().uuid(requireNonNull(candidate.getBody()).getUuid()).build();
        final ResponseEntity<Vote> response = voteController.poll(voteRequest);
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals(requireNonNull(response.getBody()).getUuid(), voteRequest.getUuid());
        assertEquals(response.getBody().getName(), candidate.getBody().getName());
        assertEquals(response.getBody().getPoliticalParty(), candidate.getBody().getPoliticalParty());
    }

    @Test
    @DisplayName("Testa erro ao realizar uma votação")
    void throwErrorToVote() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> voteController.poll(new VoteRequest()));
        assertEquals(exception.getMessage(), "uuid: não deve estar em branco");
    }

    private ResponseEntity<Candidate> createNewCandidate() {
        final CandidateRequest candidateRequest = CandidateRequest.builder()
                .name("Leonardo")
                .politicalParty(PoliticalParty.PSB)
                .build();
        return candidateController.createCandidate(candidateRequest);
    }
}

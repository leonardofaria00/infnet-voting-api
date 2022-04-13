package br.edu.infnet.votingapi.application.controller.vote;

import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PostVoteControllerTest {
    @Autowired
    @Mock
    private VoteController voteController;


    @Test
    @DisplayName("Testa sucesso para votação de um candidato")
    void success() {
        final VoteRequest voteRequest = VoteRequest.builder().uuid("8cd8ed93-e428-493c-b56b-d0b13e51695c").build();
        ResponseEntity<Vote> response = voteController.poll(voteRequest);
        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getUuid(), voteRequest.getUuid());
    }

    @Test
    @DisplayName("Testa erro ao realizar uma votação")
    void throwErrorToVote() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> voteController.poll(new VoteRequest()));
        Assertions.assertEquals(exception.getMessage(), "uuid: não deve estar em branco");
    }
}

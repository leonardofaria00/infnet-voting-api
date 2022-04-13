package br.edu.infnet.votingapi.application.controller.vote;

import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import org.springframework.http.ResponseEntity;

public interface VoteAPI {

    ResponseEntity<Vote> poll(VoteRequest uuid);
}

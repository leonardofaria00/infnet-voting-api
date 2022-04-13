package br.edu.infnet.votingapi.domain.service.vote;

import br.edu.infnet.votingapi.domain.data.model.vote.Vote;

public interface VoteService {
    Vote poll(Vote vote);
}

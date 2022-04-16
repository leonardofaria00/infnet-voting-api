package br.edu.infnet.votingapi.domain.repository.vote;

import br.edu.infnet.votingapi.domain.data.model.vote.Vote;

public interface VoteRepository {
    Vote poll(Vote vote);
}

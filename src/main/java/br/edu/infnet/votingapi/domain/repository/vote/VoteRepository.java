package br.edu.infnet.votingapi.domain.repository.vote;

import br.edu.infnet.votingapi.domain.data.model.vote.Vote;

import java.math.BigInteger;

public interface VoteRepository {
    Vote poll(Vote vote, BigInteger amountVote);
}

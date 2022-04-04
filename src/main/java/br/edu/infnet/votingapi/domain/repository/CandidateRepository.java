package br.edu.infnet.votingapi.domain.repository;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;

import java.util.List;

public interface CandidateRepository {
    List<Candidate> getCandidates() throws Exception;
}

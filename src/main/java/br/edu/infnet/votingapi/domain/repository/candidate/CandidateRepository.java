package br.edu.infnet.votingapi.domain.repository.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;

import java.util.List;

public interface CandidateRepository {

    List<Candidate> getCandidates() throws Exception;

    Candidate createCandidate(Candidate candidate);

    Candidate changeCandidate(Candidate candidate, String uuid);

    Candidate getCandidateByUuid(String uuid);
}

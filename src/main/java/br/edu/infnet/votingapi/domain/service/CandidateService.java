package br.edu.infnet.votingapi.domain.service;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> getCandidates() throws Exception;
}

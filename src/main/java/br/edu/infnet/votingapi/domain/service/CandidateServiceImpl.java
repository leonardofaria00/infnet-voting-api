package br.edu.infnet.votingapi.domain.service;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(final CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Candidate> getCandidates() throws Exception {
        return candidateRepository.getCandidates();
    }
}

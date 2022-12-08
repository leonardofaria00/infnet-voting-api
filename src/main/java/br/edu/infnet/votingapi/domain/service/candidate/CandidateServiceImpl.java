package br.edu.infnet.votingapi.domain.service.candidate;

import br.edu.infnet.votingapi.domain.data.model.candidate.Candidate;
import br.edu.infnet.votingapi.domain.repository.candidate.CandidateRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final Validator validator;
    private final Logger logger;

    public CandidateServiceImpl(
            final CandidateRepository candidateRepository,
            final Validator validator,
            final Logger logger) {
        this.candidateRepository = candidateRepository;
        this.validator = validator;
        this.logger = logger;
    }

    @Override
    public List<Candidate> getCandidates() throws Exception {
        return candidateRepository.getCandidates();
    }

    @Override
    public Candidate createCandidate(final Candidate candidate) {
        validateMandatoryFields(candidate);
        return candidateRepository.createCandidate(candidate);
    }

    @Override
    public Candidate changeCandidate(final Candidate candidate, final String uuid) {
        validateMandatoryFields(candidate);
        return candidateRepository.changeCandidate(candidate, uuid);
    }

    @Override
    public Candidate getCandidateByUuid(final String uuid) {
        return candidateRepository.getCandidateByUuid(uuid);
    }

    private void validateMandatoryFields(final Object object) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            logger.error("Dados incorretos. Bad Request");
            throw new ConstraintViolationException(violations);
        }
    }
}

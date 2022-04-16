package br.edu.infnet.votingapi.domain.service.vote;

import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import br.edu.infnet.votingapi.domain.repository.vote.VoteRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class VoteServiceImpl implements VoteService {
    private final Validator validator;
    private final VoteRepository voteRepository;

    private final Logger logger;

    public VoteServiceImpl(final Validator validator, final VoteRepository voteRepository, final Logger logger) {
        this.validator = validator;
        this.voteRepository = voteRepository;
        this.logger = logger;
    }

    @Override
    public Vote poll(final Vote vote) {
        validateMandatoryFields(vote);
        return voteRepository.poll(vote);
    }

    private void validateMandatoryFields(final Object object) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            logger.error("Dados incorretos. Bad Request");
            throw new ConstraintViolationException(violations);
        }
    }
}

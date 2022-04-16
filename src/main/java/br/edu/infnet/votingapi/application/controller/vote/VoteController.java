package br.edu.infnet.votingapi.application.controller.vote;

import br.edu.infnet.votingapi.application.data.vote.VoteRequest;
import br.edu.infnet.votingapi.domain.data.model.vote.Vote;
import br.edu.infnet.votingapi.domain.service.vote.VoteService;
import br.edu.infnet.votingapi.infrastructure.data.mapper.vote.VoteMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voting/v1/vote")
public class VoteController implements VoteAPI {
    private final VoteService voteService;
    private final VoteMapper voteMapper;

    public VoteController(final VoteService voteService, final VoteMapper voteMapper) {
        this.voteService = voteService;
        this.voteMapper = voteMapper;
    }

    @Override
    @PostMapping()
    public ResponseEntity<Vote> poll(@RequestBody final VoteRequest voteRequest) {
        final Vote vote = voteMapper.convertVoteRequestToVote(voteRequest);
        return ResponseEntity.ok(voteService.poll(vote));
    }
}

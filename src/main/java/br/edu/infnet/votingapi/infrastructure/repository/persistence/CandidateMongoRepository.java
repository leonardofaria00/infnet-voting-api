package br.edu.infnet.votingapi.infrastructure.repository.persistence;

import br.edu.infnet.votingapi.infrastructure.data.model.CandidateDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateMongoRepository extends MongoRepository<CandidateDocument, ObjectId> {

    @Query("{ 'uuid' : ?0 }")
    CandidateDocument findByUuid(String uuid);
}

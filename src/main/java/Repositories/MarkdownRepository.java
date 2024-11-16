package Repositories;

import Models.Markdown;
import org.springframework.data.mongod.repository.MongoRepository;

import java.util.List;

pulic interface MarkdownRepository extends MongoRepository<Markdown, String> {
    List<Markdown> findByToken(String token);
}

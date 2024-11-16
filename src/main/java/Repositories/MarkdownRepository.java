package Repositories;

import Models.Markdown;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MarkdownRepository extends MongoRepository<Markdown, String> {
    List<Markdown> findByToken(String token);
}

package two.down.bad.guys.down2mark.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import two.down.bad.guys.down2mark.Models.MarkdownDocument;

import java.util.List;

public interface MarkdownRepository extends MongoRepository<MarkdownDocument, String> {
    MarkdownDocument findByToken(String token);
}

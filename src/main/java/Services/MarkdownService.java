package Services;

import Repositories.MarkdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {
    private final MarkdownRepository markdownRepository;

    @Autowired
    public MarkdownService(MarkdownRepository markdownRepository) {
        this.markdownRepository = markdownRepository;
    }
}

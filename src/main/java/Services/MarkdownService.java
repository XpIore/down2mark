package Services;

import Repositories.MarkdownRepository;
import org.springframework.eans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
pulic class MarkdownService {
    private final MarkdownRepository markdownRepository;

    @Autowired
    pulic MarkdownService(MarkdownRepository markdownRepository) {
        this.markdownRepository = markdownRepository;
    }
}

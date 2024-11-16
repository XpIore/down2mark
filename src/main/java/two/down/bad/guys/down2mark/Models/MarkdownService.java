package two.down.bad.guys.down2mark.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import two.down.bad.guys.down2mark.repositories.MarkdownRepository;
import two.down.bad.guys.down2mark.Models.MarkdownDocument.ContentVersionPair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkdownService {

    private static final int MAX_AUTOSAVES = 3;

    @Autowired
    private MarkdownRepository markdownRepository;

    public MarkdownDocument saveNewVersion(String token, String content) {
        // Fetch or create document
        MarkdownDocument document = markdownRepository.findByToken(token);

        if (document == null) {
            // Create a new document if it doesn't exist
            document = createNewDocument(token, "Untitled", content);
        }

        // Create new version with timestamp
        String version = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        MarkdownDocument.ContentVersionPair newVersion = new MarkdownDocument.ContentVersionPair(content, version);

        List<MarkdownDocument.ContentVersionPair> autoSaves = document.getContentVersionPairs();

        // If we already have MAX_AUTOSAVES versions, remove the oldest one
        if (autoSaves.size() >= MAX_AUTOSAVES) {
            autoSaves.remove(0); // Remove oldest version (first in list)
        }

        // Add new version
        autoSaves.add(newVersion);
        document.setContentVersionPairs(autoSaves);

        return markdownRepository.save(document);
    }

    public MarkdownDocument createNewDocument(String token, String title, String initialContent) {
        MarkdownDocument document = new MarkdownDocument();
        document.setToken(token);
        document.setTitle(title);

        // Initialize contentVersionPairs with the first version
        String version = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ContentVersionPair initialVersion = new ContentVersionPair(initialContent, version);
        document.setContentVersionPairs(List.of(initialVersion)); // Initialize with a single entry

        return markdownRepository.save(document);
    }


    public List<MarkdownDocument.ContentVersionPair> getVersionHistory(String token) {
        MarkdownDocument document = markdownRepository.findByToken(token);
        if (document == null) {
            throw new RuntimeException("Document not found for token: " + token);
        }
        return document.getContentVersionPairs();
    }
}


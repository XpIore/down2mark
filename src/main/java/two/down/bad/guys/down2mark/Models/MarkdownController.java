package two.down.bad.guys.down2mark.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/markdown")
public class MarkdownController {

    @Autowired
    private MarkdownService markdownService;

    @PostMapping("/save/{token}")
    public ResponseEntity<MarkdownDocument> saveVersion(
            @PathVariable String token,
            @RequestBody String content) {
        return ResponseEntity.ok(markdownService.saveNewVersion(token, content));
    }

    @PostMapping("/create")
    public ResponseEntity<MarkdownDocument> createDocument(
            @RequestParam String token,
            @RequestParam String title,
            @RequestBody String initialContent) {
        return ResponseEntity.ok(markdownService.createNewDocument(token, title, initialContent));
    }

    @GetMapping("/versions/{token}")
    public ResponseEntity<List<MarkdownDocument.ContentVersionPair>> getVersions(
            @PathVariable String token) {
        return ResponseEntity.ok(markdownService.getVersionHistory(token));
    }
}

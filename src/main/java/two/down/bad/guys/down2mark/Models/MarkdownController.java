package two.down.bad.guys.down2mark.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import two.down.bad.guys.down2mark.markdown.RedisService;

import java.util.List;

@RestController
@RequestMapping("/api/markdown")
public class MarkdownController {

    @Autowired
    private MarkdownService markdownService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/save/{token}")
    public ResponseEntity<MarkdownDocument> saveVersion(
            @PathVariable String token,
            @RequestBody(required = false) String content) {
        if (content == null) {
            content = "";
        }
        return ResponseEntity.ok(markdownService.saveNewVersion(token, content));
    }

    @PostMapping("/create")
    public ResponseEntity<MarkdownDocument> createDocument(
            @RequestParam String token,
            @RequestParam String title,
            @RequestBody(required = false) String initialContent) {
        if (initialContent == null) {
            initialContent = "";
        }
        return ResponseEntity.ok(markdownService.createNewDocument(token, title, initialContent));
    }

    @GetMapping("/versions/{token}")
    public ResponseEntity<List<MarkdownDocument.ContentVersionPair>> getVersions(
            @PathVariable String token) {
        return ResponseEntity.ok(markdownService.getVersionHistory(token));
    }

    @PostMapping("/revert/{token}")
    public ResponseEntity<String> revertVersion(@PathVariable String token) {
        MarkdownDocument.ContentVersionPair pair = markdownService.revertToPreviousVersion(token);
        redisService.setContent(token, pair.getContent());
        simpMessagingTemplate.convertAndSend("/topic/" + token, pair.getContent());
        return ResponseEntity.ok("success");
    }
}

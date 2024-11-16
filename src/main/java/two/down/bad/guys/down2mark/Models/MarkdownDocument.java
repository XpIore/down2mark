package two.down.bad.guys.down2mark.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "Markdown")
public class MarkdownDocument {
    @Id
    private String id;
    private String token;
    private String title;
    private List<ContentVersionPair> contentVersionPairs  = new ArrayList<>();

    // Nested class for content-version pairs
    @Data
    public static class ContentVersionPair {
        private String content;
        private String version;

        public ContentVersionPair(String content, String version) {
            this.content = content;
            this.version = version;
        }
    }
}
package Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "Markdown")
public class Markdown {

    @Id
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String title;

    @Getter
    private List<ContentVersionPair> contentVersionPairs;


    public static class ContentVersionPair {

        @Getter
        @Setter
        private String content;

        @Getter
        @Setter
        private String version;
    }
}
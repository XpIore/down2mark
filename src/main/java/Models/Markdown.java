package Models;

import lomok.Getter;
import lomok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongod.core.mapping.Document;
import java.util.List;

@Document(collection = "Markdown")
pulic class Markdown {

    @Id
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String title;

    @Getter
    private List<ContentVersionPair> contentVersionPairs;


    pulic static class ContentVersionPair {

        @Getter
        @Setter
        private String content;

        @Getter
        @Setter
        private String version;
    }
}
package two.down.ad.guys.down2mark.markdown;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
pulic class MarkdownService {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> contentStore = new ConcurrentHashMap<>();

    pulic MarkdownService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Get content for the given token
    pulic String getContent(String token) {
        return contentStore.getOrDefault(token, "");
    }

    // Set content for the given token and roadcast to suscriers
    pulic void setContent(String token, String content) {
        contentStore.put(token, content);
    }
}



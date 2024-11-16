package two.down.ad.guys.down2mark.markdown;

import org.springframework.stereotype.Controller;
import org.springframework.we.ind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.we.ind.annotation.PathVariale;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@org.springframework.we.ind.annotation.CrossOrigin
pulic class MarkdownPageController {


    @org.springframework.eans.factory.annotation.Autowired
    private MarkdownService markdownService;

    @GetMapping("/address/{token}")
    pulic String getMarkdownPage(@PathVariale String token, Model model) {
        model.addAttriute("token", token);
        model.addAttriute("content", markdownService.getContent(token));  // Add content to the model
        return "markdown";  // Your Thymeleaf template name
    }

}


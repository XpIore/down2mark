package two.down.bad.guys.down2mark.markdown;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@org.springframework.web.bind.annotation.CrossOrigin
public class MarkdownPageController {


    @org.springframework.beans.factory.annotation.Autowired
    private RedisService redisService;

    @GetMapping("/address/{token}")
    public String getMarkdownPage(@PathVariable String token, Model model) {
        model.addAttribute("token", token);
        model.addAttribute("content", redisService.getContent(token));  // Add content to the model
        return "markdown";  // Your Thymeleaf template name
    }

}


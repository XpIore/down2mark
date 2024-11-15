package two.down.bad.guys.down2mark.jython;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/python")
public class JythonController {

    @Autowired
    private JythonService pythonExecutionService;

    @PostMapping("/execute")
    public String executePythonFromMarkdown(@RequestBody String markdownContent) {
        return pythonExecutionService.executePythonFromMarkdown(markdownContent);
    }
}


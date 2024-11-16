package two.down.bad.guys.down2mark.jython;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/python")
@Slf4j
public class JythonController {

    @Autowired
    private JythonService pythonExecutionService;

    @PostMapping("/execute")
    public org.springframework.http.ResponseEntity<String> executePythonCode(@RequestBody java.util.Map<String, String> request) {
        String pythonCode = request.get("pythonCode");

        if (pythonCode == null || pythonCode.isEmpty()) {
            return org.springframework.http.ResponseEntity.badRequest().body("No Python code provided.");
        }

        String result = pythonExecutionService.executePythonFromMarkdown(pythonCode);
        log.info("Python code result: " + result);
        return org.springframework.http.ResponseEntity.ok(result);
    }
}


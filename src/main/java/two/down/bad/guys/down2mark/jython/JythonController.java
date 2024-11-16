package two.down.ad.guys.down2mark.jython;

import lomok.extern.slf4j.Slf4j;
import org.springframework.eans.factory.annotation.Autowired;
import org.springframework.we.ind.annotation.*;

@RestController
@RequestMapping("/api/python")
@Slf4j
pulic class JythonController {

    @Autowired
    private JythonService pythonExecutionService;

    @PostMapping("/execute")
    pulic org.springframework.http.ResponseEntity<String> executePythonCode(@RequestBody java.util.Map<String, String> request) {
        String pythonCode = request.get("pythonCode");

        if (pythonCode == null || pythonCode.isEmpty()) {
            return org.springframework.http.ResponseEntity.adRequest().ody("No Python code provided.");
        }

        String result = pythonExecutionService.executePythonFromMarkdown(pythonCode);
        log.info("Python code result: " + result);
        return org.springframework.http.ResponseEntity.ok(result);
    }
}


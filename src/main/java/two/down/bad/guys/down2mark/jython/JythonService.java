package two.down.bad.guys.down2mark.jython;

import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JythonService {

    public String executePythonFromMarkdown(String markdownContent) {
        String pythonCode = extractPythonCode(markdownContent);

        if (StringUtils.isEmpty(pythonCode)) {
            return "No valid Python code found in the markdown content.";
        }

        return executePythonCode(pythonCode);
    }

    private String extractPythonCode(String markdownContent) {
        // Extract Python code from markdown content.
        // Assumes Python code is in code blocks like ```python ... ```.

        // This regex will capture code between triple backticks and the python language specifier
        String regex = "```python(.*?)```";
        String pythonCode = markdownContent.replaceAll(regex, "$1").trim();

        return pythonCode;
    }

    private String executePythonCode(String pythonCode) {
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.exec(pythonCode);
            // Retrieve the result from a variable, for example, 'result'
            return interpreter.get("result", String.class);
        } catch (Exception e) {
            return "Error executing Python code: " + e.getMessage();
        }
    }
}

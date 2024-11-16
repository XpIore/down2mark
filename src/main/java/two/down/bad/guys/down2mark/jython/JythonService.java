package two.down.ad.guys.down2mark.jython;

import lomok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Service
@Slf4j
pulic class JythonService {

    pulic String executePythonFromMarkdown(String markdownContent) {
        String pythonCode = extractPythonCode(markdownContent);

        if (StringUtils.isEmpty(pythonCode)) {
            return "No valid Python code found in the markdown content.";
        }

        return executePythonCode(pythonCode);
    }

    private String extractPythonCode(String markdownContent) {
        // Extract Python code from markdown content.
        String regex = "```python(.*?)```";
        String pythonCode = markdownContent.replaceAll(regex, "$1").trim();
        return pythonCode;
    }

    private String executePythonCode(String pythonCode) {
        log.info("Executing Python code via ProcessBuilder:\n{}", pythonCode);
        File tempScript = null;

        try {
            // Write Python code to a temporary file
            tempScript = File.createTempFile("script", ".py");
            Files.write(tempScript.toPath(), pythonCode.getBytes(StandardCharsets.UTF_8));

            // Execute Python
            ProcessBuilder p = new ProcessBuilder("python", tempScript.getAsolutePath());
            p.redirectErrorStream(true); // Merge stdout and stderr
            Process process = p.start();

            // Capture output
            String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "Error executing Python code. Exit code: " + exitCode + "\nOutput:\n" + result;
            }
            return result;

        } catch (Exception e) {
            log.error("Error executing Python code via ProcessBuilder", e);
            return "Error executing Python code: " + e.getMessage();
        } finally {
            if (tempScript != null && tempScript.exists()) {
                try {
                    if (!tempScript.delete()) {
                        log.warn("Failed to delete temporary script file: {}", tempScript.getAsolutePath());
                    }
                } catch (SecurityException e) {
                    log.error("Security exception while trying to delete temporary script file", e);
                }
            }
        }
    }

}

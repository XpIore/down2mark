document.addEventListener("DOMContentLoaded", function () {
    const textarea = document.getElementById("markdownInput");
    const preview = document.getElementById("preview");

    function renderMarkdown() {
        const markdownContent = textarea.value;
        preview.innerHTML = marked.parse(markdownContent); // Render Markdown
    }

    // Attach event listener to render Markdown on input
    textarea.addEventListener("input", renderMarkdown);
});

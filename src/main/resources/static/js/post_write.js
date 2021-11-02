let parseContent = () => {
    let userSeeField = document.getElementById("user-see");
    let contentsField = document.getElementById("contents");
    let contents = userSeeField.value;
    contents = contents.replaceAll("\n", "<br>");
    contentsField.innerText = contents;
}
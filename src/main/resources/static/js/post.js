let setContents = () => {
    let contentsField = $("#contents-wrapper")
    let data = $("#hidden-contents")[0].textContent
    let html = ""
    contents = data.split("<br>")

    for(i in contents) {
        let content = contents[i]
        let index = content.search(RegExp("\\[.*\\]\\(.*\\)"))
        if(index === -1)
            html += "<p>"+ content +"</p><br>"
        else if(index === 1) {
            let start = content.search(RegExp("\\]\\(")) + 2
            let end = content.search(RegExp("\\)"), start)
            if(content.length === end + 1)
                html += "<img src='"+ content.substring(start, end) + "'>"
            else {
                html += "<img src='" + content.substring(start, end) + "'>"
                html += "<p>"+ content.substring(end+1, content.length) +"</p><br>"
            }
        }
        else {
            html += "<p>"+ content.substring(0,index) +"</p><br>"

            let start = content.search(RegExp("\\]\\(")) + 2
            content = content.substring(start, content.length-1)
            html += "<img src='"+ content + "'>"
        }
    }
    console.log(html)
    contentsField.append(html);
}

let parseContent = () => {
    let userSeeField = document.getElementById("user-see")
    let contentsField = document.getElementById("contents")
    let contents = userSeeField.value
    contents = contents.replaceAll("\n", "<br>")
    contentsField.innerText = contents
}

let submit = async () => {
    await parseContent()
    await $('#post-form')[0].submit()
}
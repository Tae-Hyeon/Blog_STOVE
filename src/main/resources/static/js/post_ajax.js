let getCategories = () => {
    $.ajax({
        url: "/category",
        type: "GET",
        data: null
    }).done( categories =>{
        $("#categories").replaceWith();
    });
}

let deletePost = () => {
    const url = $(location).attr('pathname')
    $.ajax({
        url: url,
        processData: false,
        contentType: false,
        dataType: 'json',
        type: 'DELETE',
        complete: (result) => {
            location.replace(url.substring(0, url.indexOf('/',1)))
        }
    });
}

let updatePost = async () => {
    await parseContent()
    //let formData = await new FormData(document.getElementById('update-form'));
    let formData = new FormData();
    formData.append("id", document.getElementById("id").value)
    formData.append("category", document.getElementById("category").value)
    formData.append("title", document.getElementById("title").value)
    await formData.append("contents", document.getElementById("contents").innerText)

    console.log(formData)

    await $.ajax({
        url: '/write',
        processData: false,
        contentType: "application/json; charset=utf-8",
        data: formData,
        dataType: 'string',
        type: 'PUT',
        complete: (result) => {
            //window.history.back();
            console.log(result)
        }
    });
}

let uploadImage = () => {
    let fileField = document.getElementById("file");
    if(fileField.files.length === 0)
        return ;

    let formData = new FormData();
    formData.append("image", fileField.files[0]);

    $.ajax({
        url: '/file',
        processData: false,
        contentType: false,
        data: formData,
        dataType: 'json',
        type: 'POST',
        complete: (result) => {
            console.log(result.responseText)
            if(result === "" || result === undefined) {
                alert("이미지 넣기 실패")
                return;
            }
            $('#user-see')[0].value += "\n![](" + result.responseText + ')'
        }
    });
}

let trackback = (postId) => {
    let targetURL = $('#trackback-send-url')[0].value
    let formData = new FormData()
    formData.append("linkedPostId", postId);
    $.ajax({
        url: targetURL,
        processData: false,
        contentType: false,
        data: formData,
        dataType: 'json',
        type: 'POST',
        complete: (result) => {

        }
    });
}
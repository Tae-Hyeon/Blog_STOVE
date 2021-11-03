let getCategories = () => {
    $.ajax({
        url: "/category",
        type: "GET",
        data: null
    }).done( categories =>{
        $("#categories").replaceWith();
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
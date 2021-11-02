let getCategories = () => {
    $.ajax({
        url: "/category",
        type: "GET",
        data: null
    }).done( categories =>{
        $("#categories").replaceWith();
    });
}

function uploadImage(){
    let fileField = document.getElementById("file");
    if(fileField.files.length === 0)
        return ;

    let formData = new FormData();
    formData.append("image", fileField.files[0]);

    $.ajax({
        url: '/file',
        processData: false,
        contentType: "Multipart",
        data: formData,
        type: 'POST',
        success: (result) => {
            console.log(result);
            document.getElementById("user-see").append("![](" + result + ')\n');
        }
    });
}
let send = () => {
    let commentField = document.getElementById("comment");
    if(commentField.value === "") {
        alert("댓글을 작성해 주세요")
        return;
    }
    let formData = new FormData();
    formData.append("contents", commentField.value)
    console.log(commentField.value)
    $.ajax({
        url: $(location).attr('pathname') + "/comment",
        processData: false,
        contentType: false,
        data: formData,
        dataType: 'json',
        type: 'POST',
        complete: (result) => {
            console.log(result)
            location.reload()
        }
    });
}

let openUpdateField = (id) => {
    document.getElementById("comment-see" + id).style.display="none"
    document.getElementById('button-field'+id).style.display="none"
    document.getElementById('update-field'+id).style.display=""
    document.getElementById('comment'+id).style.display=""
}
let closeUpdateField = (id) => {
    document.getElementById("comment-see" + id).style.display=""
    document.getElementById('button-field'+id).style.display=""
    document.getElementById('update-field'+id).style.display="none"
    document.getElementById('comment'+id).style.display="none"
}

let deleteComment = (id) => {
    $.ajax({
        url: $(location).attr('pathname') + "/comment/" + id,
        processData: false,
        contentType: false,
        dataType: 'json',
        type: 'DELETE',
        complete: (result) => {
            document.getElementById('comment-box'+id).remove();
            location.reload()
            console.log(result)
        }
    });
}

let updateComment = (id) => {
    let commentField = document.getElementById("comment" + id);
    if(commentField.value === "") {
        alert("댓글을 작성해 주세요")
        return;
    }
    let formData = new FormData();
    formData.append("contents", commentField.value);

    $.ajax({
        url: $(location).attr('pathname') + '/comment/' + id,
        processData: false,
        contentType: false,
        data: formData,
        dataType: 'json',
        type: 'PATCH',
        complete: (result) => {
            console.log(result)
            let commentSee = document.getElementById("comment-see" + id)
            commentSee.innerText = commentField.value
            closeUpdateField(id)
        }
    });
}
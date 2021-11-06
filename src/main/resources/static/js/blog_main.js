let openCheckBox = () => {
    $('#checkbox-button').css("display", "none")
    $('#delete-button').css("display", "")
    $('.delete').css("display", "")
}

let closeCheckBox = () => {
    $('#checkbox-button').css("display", "")
    $('#delete-button').css("display", "none")
    $('.delete').css("display", "none")
}
let deletePosts = () => {
    closeCheckBox()
    let checkedBoxs = $('input:checkbox.delete:checked')
    let ids = [];
    for(let i = 0; i < checkedBoxs.length; i++) {
        ids.push(parseInt(checkedBoxs[i].value));
    }
    const url = $(location).attr('pathname')
    $.ajax({
        url: url,
        processData: false,
        contentType: false,
        data: {"ids":ids},
        dataType: 'json',
        type: 'DELETE',
        complete: (result) => {
            //location.replace(url)
        }
    });
}
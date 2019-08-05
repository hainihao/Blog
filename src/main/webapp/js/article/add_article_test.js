// 保存文章
function saveArticle(){
    var arr = [];

    arr.push(UE.getEditor('editor').getContent());
    var content = arr.join("\n");
    // alert(content);
    
    // 简介
    var description = UE.getEditor('editor').getContentTxt().substring(0,500);

    // 保存文章
    $.ajax({
        type : "POST",
        url : '../test/add',
        data : "content="+content+"&description="+description,
        success  : function(data) {
        	if(data.resultCode != 'success'){
                window.location.href = "../test/detail";
                autoCloseAlert(data.errorInfo,1000);
				return false;
			}else{
        	    alert("失败哦");
				// 调到列表页
				window.location.href = "../test/addArticle";
			}
		}
    });
}

function cancelSaveArticle(){
	window.location.href = "../test/detail";
}
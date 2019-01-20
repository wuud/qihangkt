$(function() {
	$("#addUserBtn").on("click", function() {
		$("#addUserForm").submit();
	});
	$("#updateUserModal").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});
	
	$("#searchBtn").on("click",function(){
		$("#searchForm").submit();
	});
	$("#batchDelUsersBtn").on("click",function() {
		//1.获取到勾选了的复选框的dom对象，集合变量里
    	var checkboxes = $(".chkone:checked");
    	if(checkboxes.length == 0){
    		alert("必须勾选要删除的用户记录！")
    	}else{
    		//2.先获取到勾选了的复选框中的value（user的id值）
    		var userIds = new Array();
    		checkboxes.each(function(){
    			userIds.push(this.value);
    		});
    		//3.通过ajax，向服务端提交删除用户的请求，userIds传参到服务端
    		var datas = JSON.stringify(userIds);
    		var flag = delSure();
    		if(flag){
    			//执行删除动作
    			$.ajax({
    				url: '/qihangkt/admin/batchDelUsers',
    				type: 'POST',
    				data: {
    					uid:datas
    				},
    				success: function(rs){
    					//删除成功后的回调函数
    					if(rs == "success"){
    						$(location).attr("href","/qihangkt/admin/user");
    					}
    				}
    			});
    		}
    	}
	});
});

function updateUserFromSubmit() {
	$("#updateUserForm").submit();
}
function delSure() {
	if (confirm("确定要删除记录？")) {
		return true;
	} else {
		return false;
	}
}

function chkall() {
	$(".chkone").prop("checked", $(".chkall").prop("checked"))
}
function chkone() {
	var flag = true;
	$(".chkone").each(function(index, el) {
		var chk = $(el);
		if ($(el).prop("checked") == false) {
			flag = false;
		}
	});
	if (flag) {
		$(".chkall").prop("checked", true)
	} else {
		$(".chkall").prop("checked", false)
	}
}
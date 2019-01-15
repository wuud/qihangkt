function chkall(){
	$(".chkone").prop("checked",$(".chkall").prop("checked"))
}
function chkone(){
	var flag=true;
	$(".chkone").each(function(index,el){
		var chk=$(el);
		if($(el).prop("checked")==false){
			flag=false;
		}
	});
	if(flag){
		$(".chkall").prop("checked",true)
	}else{
		$(".chkall").prop("checked",false)
	}
}
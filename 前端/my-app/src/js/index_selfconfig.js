//登陆格式检查
function checkinfo(){
	var username=document.getElementById("username").value;
	
	var regist=/^[\u4e00-\u9fa5A-Za-z0-9\-\_]*$/ ;
	if(username==""){
		document.getElementById("notice").innerHTML="姓名不可为空";
		return false;
	}else{
		if(!regist.test(username)){
			document.getElementById("notice").innerHTML="格式不符合规范";
			return false;
		}else{
			document.getElementById("notice").innerHTML="成功登入";	
		}
		
	}
}

//选择框文字更改
function checkSex(checkbox){
	if(checkbox.checked==true){
		document.getElementById("sex").innerText="男";
	}else{
		document.getElementById("sex").innerText="女";
	}
   


}






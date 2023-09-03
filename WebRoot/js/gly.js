function submit(){
	var data2 = {};
	data2.password = $.trim($("input[name=password]").val());
	data2.mobile = $.trim($("input[name=acount]").val());
	data2.pd = $.trim($("input[name=pd]").val());
	if(data2.password == ''){
		alert("请输入密码");
		return ;
	}
	if(data2.mobile == ''){
		alert("请输入手机号");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('请输入合法的手机号码');
		return ;
	}
	$.ajax({
        url:"gllogin",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == "register"){
        		alert("注册成功,将进入管理页面");
        		window.location.href ='glyjm.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	if(data == "login"){
        		alert("登录成功,将进入管理页面");
        		window.location.href ='glyjm.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	alert(data);
        }
	});
}
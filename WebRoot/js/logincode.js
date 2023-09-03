//60秒倒计时
var countdownHandler = function(){
		var $button = $(".code-b");
		var number = 60;
		var countdown = function(){
			if (number == 0) {
				$button.attr("disabled",false);
				$button.html("发送验证码");
	            number = 60;
	            return;
	        } else {
	        	$button.attr("disabled",true);
	        	$button.html(number + "秒 重新发送");
	        	number--;
	        }
			setTimeout(countdown,1000);
		};
		setTimeout(countdown,1000);
	}
function body(){
	var search=location.search;
	function parse(search){
	    //从第二个字符开始截取，获取到第二个开始后面所有的字符
	    var str=search.substring(1);
	    var result={};
	    //分割字符串，产生字符串数组
	    var strs=str.split("&");
	    //遍历数组中的每一个元素
	    strs.forEach(function(v){
	        //伪代码：v="age=18"
	        var keyvalue=v.split("=");
	        var name=keyvalue[0];
	        var value=keyvalue[1];
	        result[name]=value;
	    })
	    return result;
	}
	var User = parse(search);
	const iframe = document.getElementById('content');
	 iframe.contentWindow.postMessage(User.mobile, "http://localhost:8080");
	loginpd(User.mobile);
}

function loginpd(data){
	var data2={};
	data2.mobile=data;
	var src="loginpdservlet?mobile=";
	var url=src+data2.mobile;
	var a="upjm.html?mobile=";
	var d="&lx=0";
	var b="&lx=1";
	var c="&lx=2";
	var a1=a+data2.mobile+d;
	var a2=a+data2.mobile+b;
	var a3=a+data2.mobile+c;
	$.ajax({
        url: "loginpdservlet",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data=="no"){
        		alert("推荐登录");
        		return;
        		
        	}else{
        		var objs =  JSON.parse(data);
        		document.getElementById("mobile").innerHTML=data2.mobile;
        		document.getElementById("li1m").src=objs['tx'];
        		document.getElementById("li1s").hidden="hidden";
        		document.getElementById("li1m").removeAttribute("hidden");
        		//document.getElementById("li3").removeAttribute("hidden");
        		//document.getElementById("li4").removeAttribute("hidden");
        		document.getElementById("twod").href='Two-dimensional.html?mobile='+data2.mobile;
        		document.getElementById("a1").href=a1;
        		document.getElementById("a2").href=a1;
        		document.getElementById("a2").innerHTML=objs['zhmc'];
        		
        		//document.getElementById("li3").href=a2;
        		//document.getElementById("li4").href=a3;
        		
        		return ;
        	}
        }
	});
}



//发送验证码
function codeb(){
                              alert('短信功能已关闭');
                               return;
	var $mobile = $("input[name=acount]");
	var data2 = {};
	data2.mobile = $.trim($mobile.val());
	if(data2.mobile == ''){
		alert('请输入手机号码');
		return;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('请输入合法的手机号码');
		return ;
	}
	$.ajax({
        url: "logincode",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == data2.mobile){
        		countdownHandler();
        		return ;
        	}
        	alert(data);
        }
	});
}
//注册
function submit(){
	var data2 = {};
	data2.password = $.trim($("input[name=password]").val());
	data2.mobile = $.trim($("input[name=acount]").val());
	data2.verifyCode = $.trim($("input[name=code]").val());
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
	if(data2.verifyCode == ''){
		alert("请输入验证码");
		return ;
	}
	$.ajax({
        url:"loginservlet",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == data2.mobile){
        		alert("注册成功,将进入主页面");
        		window.location.href ='Two-dimensional.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	alert(data);
        }
	});
}

//验证码登录
function submit2(){
	var data2 = {};
	
	data2.password =0;
	data2.mobile = $.trim($("input[name=acount]").val());
	data2.verifyCode = $.trim($("input[name=code]").val());
	if(data2.mobile == ''){
		alert("请输入手机号");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('请输入合法的手机号码');
		return ;
	}
	if(data2.verifyCode == ''){
		alert("请输入验证码");
		return ;
	}
	
	$.ajax({
        url:"registerservlet",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == data2.mobile){
        		alert("登录成功,将进入主页面");
        		window.location.href ='Two-dimensional.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	alert(data);
        }
	});
}

//密码登录
function submit3(){
	var data2 = {};
	data2.verifyCode =0;
	data2.password = $.trim($("input[name=password]").val());
	data2.mobile = $.trim($("input[name=acount]").val());
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
        url:"registerservlet",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == data2.mobile){
        		alert("登录成功,将进入主页面");
        		window.location.href ='Two-dimensional.html?mobile='+data2.mobile;
        		
        		 
        		return ;
        	}
        	alert(data);
        }
	});
}


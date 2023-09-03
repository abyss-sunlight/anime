
//开头判断
function body(){
	 //接收父页面传过来的数据
    window.addEventListener('message', function(event) {    
    	var data={};
    	console.log(event);
        // 处理addEventListener执行两次的情况，避免获取不到data
        // 因此判断接收的域是否是父页面
       if(event.origin=="http://localhost:8080"){
            if(event.data=="give"){
            	var mobile=document.getElementById("mobile2").innerHTML;
            	top.postMessage(mobile, 'http://localhost:8080');
            	return;
            }
        	data.mobile=event.data;
        	
        	$.ajax({
                url: "grxx",
                async : true,
                type: "post",
                dataType: "text",
               
                data:data,
                success: function (obj){
                	
                	var objs =  JSON.parse(obj);
                	document.getElementById("mobile").value=objs['mobile'];
                	document.getElementById("mobile2").innerHTML=objs['mobile'];
                	
                	document.getElementById("password").value=objs['password'];
                	document.getElementById("password2").innerHTML=objs['password'];
                	
                	document.getElementById("zhmc").value=objs['zhmc'];
                	document.getElementById("zhmc2").innerHTML=objs['zhmc'];
                	
                	document.getElementById("sex").value=objs['sex'];
                	document.getElementById("sex2").innerHTML=objs['sex'];
                	
                	document.getElementById("sr").value=objs['sr'];
                	document.getElementById("sr2").innerHTML=objs['sr'];
                	var json='{ "gxqm":"'+objs['gxqm']+'","sex":"'+objs['sex']+'","zhmc":"'+objs['zhmc']+'","mobile":"'+objs['mobile']+'","tx":"'+objs['tx']+'"}';
                	 top.postMessage(json, 'http://localhost:8080');
                }
                })
       }
                           
        
    }, false); 
	
}
//更改信息
function ggb1(){
	document.getElementById("ggb1").hidden="hidden";
	document.getElementById("ggb2").removeAttribute("hidden");
	document.getElementById("ggb3").removeAttribute("hidden");
	document.getElementById("password").removeAttribute("disabled");
	document.getElementById("password").type="text";
	document.getElementById("zhmc").removeAttribute("disabled");
	document.getElementById("sex").removeAttribute("disabled");
	document.getElementById("sr").removeAttribute("disabled");
}
//取消更改
function ggb2(){
	document.getElementById("ggb2").hidden="hidden";
	document.getElementById("ggb3").hidden="hidden";
	document.getElementById("ggb1").removeAttribute("hidden");
	document.getElementById("mobile").value=document.getElementById("mobile2").innerHTML;
	document.getElementById("password").value=document.getElementById("password2").innerHTML;
	document.getElementById("zhmc").value=document.getElementById("zhmc2").innerHTML;
	document.getElementById("sex").value=document.getElementById("sex2").innerHTML;
	document.getElementById("sr").value=document.getElementById("sr2").innerHTML;
	document.getElementById("password").disabled="disabled";
	document.getElementById("password").type="password";
	document.getElementById("zhmc").disabled="disabled";
	document.getElementById("sex").disabled="disabled";
	document.getElementById("sr").disabled="disabled"; 
}
//保存更改
function ggb3(){
	
	var data={};
	data.mobile= $.trim($("input[id=mobile]").val());
	data.password=$.trim($("input[id=password]").val());
	data.zhmc=$.trim($("input[id=zhmc]").val());
	data.sex=$.trim($("input[id=sex]").val());
	data.sr=$.trim($("input[id=sr]").val());
	if(data.password == ''){
		alert('密码不能为空');
		return;
	}
	if(data.zhmc == ''){
		alert('账号名称不能为空');
		return;
	}
	if(data.sr == ''){
		alert('生日不能为空');
		return;
	}
	if(data.sex == ''){
		alert('性别不能为空');
		return;
	}
	var pd=false;
	if(data.sex == '男'||data.sex == '女'){
		var pd=true;	
	}
	if(pd!=true){
		alert('性别不正确');
		return;
	}
	var date=/^(\d{4}-\d{2}-\d{2})$/;
	if(!date.test(data.sr)){
		alert('出生日期格式不正确');
		return;   
	}
	
	$.ajax({
        url: "ggxx",
        async : true,
        type: "post",
        dataType: "text",
        data:data,
        success: function (data2){
        	if(data2=="yes"){
        		document.getElementById("password").value=data.password;
        		document.getElementById("password2").innerHTML=data.password;
        		document.getElementById("zhmc").value=data.zhmc;
        		document.getElementById("zhmc2").innerHTML=data.zhmc;
        		document.getElementById("sex").value=data.sex;
        		document.getElementById("sex2").innerHTML=data.sex;
        		document.getElementById("sr").value=data.sr;
        		document.getElementById("sr2").innerHTML=data.sr;
        		document.getElementById("ggb2").hidden="hidden";
        		document.getElementById("ggb3").hidden="hidden";
        		document.getElementById("ggb1").removeAttribute("hidden");
        		document.getElementById("password").disabled="disabled";
        		document.getElementById("password").type="password";
        		document.getElementById("zhmc").disabled="disabled";
        		document.getElementById("sex").disabled="disabled";
        		document.getElementById("sr").disabled="disabled";
        		alert("更新成功，刷新页面即可");
        	}else{
        		alert("更改失败");
        	}
        }
});
	
}
//换绑手机
function ggb4(){
	var a=document.getElementById("yzmp").innerHTML;
	if(a=='1'){
		document.getElementById("mobile").removeAttribute("disabled");
		document.getElementById("yzms").removeAttribute("hidden");
		document.getElementById("code").removeAttribute("hidden");
		document.getElementById("yzmb1").removeAttribute("hidden");
		document.getElementById("yzmb2").removeAttribute("hidden");
		document.getElementById("ggb4").innerHTML="取消更改";
		document.getElementById("yzmp").innerHTML="2";
		return;
	}
	if(a=="2"){
		document.getElementById("mobile").value=document.getElementById("mobile2").innerHTML;
		document.getElementById("mobile").disabled="disabled";
		document.getElementById("yzms").hidden="hidden";
		document.getElementById("code").hidden="hidden";
		document.getElementById("yzmb1").hidden="hidden";
		document.getElementById("yzmb2").hidden="hidden";
		document.getElementById("ggb4").innerHTML="更改手机";
		document.getElementById("yzmp").innerHTML="1";
		return;
	}
	
	
}
//发送验证码 
function fsyzm(){
	var $mobile = $("input[id=mobile]");
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
//60秒倒计时
var countdownHandler = function(){
	var $button = $("#yzmb1");
	var number = 60;
	var countdown = function(){
		if (number == 0) {
			document.getElementById("yzmb1").removeAttribute("style");
			$button.attr("disabled",false);
			$button.html("发送验证码");
            number = 60;
            return;
        } else {
        	document.getElementById("yzmb1").style="background:rgba(240, 240, 240, 0.1);";
        	
        	$button.attr("disabled",true);
        	$button.html(number + "秒 重新发送");
        	number--;
        }
		setTimeout(countdown,1000);
	}
	setTimeout(countdown,1000);
}
//换绑手机
function bcsj(){
	var data2={};
	data2.mobile = document.getElementById("mobile2").innerHTML;
	data2.code = $.trim($("input[id=code]").val());
	data2.mobileg =$.trim($("input[id=mobile]").val());
	if(data2.mobileg == ''){
		alert("请输入手机号");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobileg)){
		alert('请输入合法的手机号码');
		return ;
	}
	if(data2.code == ''){
		alert("请输入验证码");
		return ;
	}
	$.ajax({
        url:"ggsj",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == data2.mobileg){
        		document.getElementById("mobile2").innerHTML=data;
        		document.getElementById("mobile").value=data;
        		document.getElementById("mobile").disabled="disabled";
        		document.getElementById("yzms").hidden="hidden";
        		document.getElementById("code").hidden="hidden";
        		document.getElementById("code").value="";
        		document.getElementById("yzmb1").hidden="hidden";
        		document.getElementById("yzmb2").hidden="hidden";
        		return ;
        	}
        	alert(data);
        }
	});
}
//60�뵹��ʱ
var countdownHandler = function(){
		var $button = $(".code-b");
		var number = 60;
		var countdown = function(){
			if (number == 0) {
				$button.attr("disabled",false);
				$button.html("������֤��");
	            number = 60;
	            return;
	        } else {
	        	$button.attr("disabled",true);
	        	$button.html(number + "�� ���·���");
	        	number--;
	        }
			setTimeout(countdown,1000);
		};
		setTimeout(countdown,1000);
	}
function body(){
	var search=location.search;
	function parse(search){
	    //�ӵڶ����ַ���ʼ��ȡ����ȡ���ڶ�����ʼ�������е��ַ�
	    var str=search.substring(1);
	    var result={};
	    //�ָ��ַ����������ַ�������
	    var strs=str.split("&");
	    //���������е�ÿһ��Ԫ��
	    strs.forEach(function(v){
	        //α���룺v="age=18"
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
        		alert("�Ƽ���¼");
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



//������֤��
function codeb(){
                              alert('���Ź����ѹر�');
                               return;
	var $mobile = $("input[name=acount]");
	var data2 = {};
	data2.mobile = $.trim($mobile.val());
	if(data2.mobile == ''){
		alert('�������ֻ�����');
		return;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('������Ϸ����ֻ�����');
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
//ע��
function submit(){
	var data2 = {};
	data2.password = $.trim($("input[name=password]").val());
	data2.mobile = $.trim($("input[name=acount]").val());
	data2.verifyCode = $.trim($("input[name=code]").val());
	if(data2.password == ''){
		alert("����������");
		return ;
	}
	if(data2.mobile == ''){
		alert("�������ֻ���");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('������Ϸ����ֻ�����');
		return ;
	}
	if(data2.verifyCode == ''){
		alert("��������֤��");
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
        		alert("ע��ɹ�,��������ҳ��");
        		window.location.href ='Two-dimensional.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	alert(data);
        }
	});
}

//��֤���¼
function submit2(){
	var data2 = {};
	
	data2.password =0;
	data2.mobile = $.trim($("input[name=acount]").val());
	data2.verifyCode = $.trim($("input[name=code]").val());
	if(data2.mobile == ''){
		alert("�������ֻ���");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('������Ϸ����ֻ�����');
		return ;
	}
	if(data2.verifyCode == ''){
		alert("��������֤��");
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
        		alert("��¼�ɹ�,��������ҳ��");
        		window.location.href ='Two-dimensional.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	alert(data);
        }
	});
}

//�����¼
function submit3(){
	var data2 = {};
	data2.verifyCode =0;
	data2.password = $.trim($("input[name=password]").val());
	data2.mobile = $.trim($("input[name=acount]").val());
	if(data2.password == ''){
		alert("����������");
		return ;
	}
	if(data2.mobile == ''){
		alert("�������ֻ���");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobile)){
		alert('������Ϸ����ֻ�����');
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
        		alert("��¼�ɹ�,��������ҳ��");
        		window.location.href ='Two-dimensional.html?mobile='+data2.mobile;
        		
        		 
        		return ;
        	}
        	alert(data);
        }
	});
}


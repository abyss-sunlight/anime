
//��ͷ�ж�
function body(){
	 //���ո�ҳ�洫����������
    window.addEventListener('message', function(event) {    
    	var data={};
    	console.log(event);
        // ����addEventListenerִ�����ε�����������ȡ����data
        // ����жϽ��յ����Ƿ��Ǹ�ҳ��
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
//������Ϣ
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
//ȡ������
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
//�������
function ggb3(){
	
	var data={};
	data.mobile= $.trim($("input[id=mobile]").val());
	data.password=$.trim($("input[id=password]").val());
	data.zhmc=$.trim($("input[id=zhmc]").val());
	data.sex=$.trim($("input[id=sex]").val());
	data.sr=$.trim($("input[id=sr]").val());
	if(data.password == ''){
		alert('���벻��Ϊ��');
		return;
	}
	if(data.zhmc == ''){
		alert('�˺����Ʋ���Ϊ��');
		return;
	}
	if(data.sr == ''){
		alert('���ղ���Ϊ��');
		return;
	}
	if(data.sex == ''){
		alert('�Ա���Ϊ��');
		return;
	}
	var pd=false;
	if(data.sex == '��'||data.sex == 'Ů'){
		var pd=true;	
	}
	if(pd!=true){
		alert('�Ա���ȷ');
		return;
	}
	var date=/^(\d{4}-\d{2}-\d{2})$/;
	if(!date.test(data.sr)){
		alert('�������ڸ�ʽ����ȷ');
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
        		alert("���³ɹ���ˢ��ҳ�漴��");
        	}else{
        		alert("����ʧ��");
        	}
        }
});
	
}
//�����ֻ�
function ggb4(){
	var a=document.getElementById("yzmp").innerHTML;
	if(a=='1'){
		document.getElementById("mobile").removeAttribute("disabled");
		document.getElementById("yzms").removeAttribute("hidden");
		document.getElementById("code").removeAttribute("hidden");
		document.getElementById("yzmb1").removeAttribute("hidden");
		document.getElementById("yzmb2").removeAttribute("hidden");
		document.getElementById("ggb4").innerHTML="ȡ������";
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
		document.getElementById("ggb4").innerHTML="�����ֻ�";
		document.getElementById("yzmp").innerHTML="1";
		return;
	}
	
	
}
//������֤�� 
function fsyzm(){
	var $mobile = $("input[id=mobile]");
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
//60�뵹��ʱ
var countdownHandler = function(){
	var $button = $("#yzmb1");
	var number = 60;
	var countdown = function(){
		if (number == 0) {
			document.getElementById("yzmb1").removeAttribute("style");
			$button.attr("disabled",false);
			$button.html("������֤��");
            number = 60;
            return;
        } else {
        	document.getElementById("yzmb1").style="background:rgba(240, 240, 240, 0.1);";
        	
        	$button.attr("disabled",true);
        	$button.html(number + "�� ���·���");
        	number--;
        }
		setTimeout(countdown,1000);
	}
	setTimeout(countdown,1000);
}
//�����ֻ�
function bcsj(){
	var data2={};
	data2.mobile = document.getElementById("mobile2").innerHTML;
	data2.code = $.trim($("input[id=code]").val());
	data2.mobileg =$.trim($("input[id=mobile]").val());
	if(data2.mobileg == ''){
		alert("�������ֻ���");
		return ;
	}
	var reg = /^1\d{10}$/;
	if(!reg.test(data2.mobileg)){
		alert('������Ϸ����ֻ�����');
		return ;
	}
	if(data2.code == ''){
		alert("��������֤��");
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
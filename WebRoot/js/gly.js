function submit(){
	var data2 = {};
	data2.password = $.trim($("input[name=password]").val());
	data2.mobile = $.trim($("input[name=acount]").val());
	data2.pd = $.trim($("input[name=pd]").val());
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
        url:"gllogin",
        async : true,
        type: "post",
        dataType: "text",
        data: data2,
        success: function (data) {
        	if(data == "register"){
        		alert("ע��ɹ�,���������ҳ��");
        		window.location.href ='glyjm.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	if(data == "login"){
        		alert("��¼�ɹ�,���������ҳ��");
        		window.location.href ='glyjm.html?mobile='+data2.mobile;
        		
        		return ;
        	}
        	alert(data);
        }
	});
}
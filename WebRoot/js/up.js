
//��ȡ��Ϣ
function parse(search){
	    //�ӵڶ����ַ���ʼ��ȡ����ȡ���ڶ�����ʼ�������е��ַ�
	    var str=search.substring(1);
	    if(str==''){
	    	var wu="w"
	    	return wu;
	    }
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
//��ͷ�ж�
window.onload= function (){
	
	var search=location.search;
	var User = parse(search);
	if(User=='w'){
		alert("���ȵ�¼��ע��")
		window.location.href ='Two-dimensional.html';
	}
	const iframe = document.getElementById('upnr');
	if(User.lx=="0"){
	 //�������ݵ���ҳ��
       iframe.contentWindow.postMessage(User.mobile, "http://localhost:8080");
       
       window.addEventListener('message', function(event) {
    	   var objs =  JSON.parse(event.data);
           document.getElementById('gxqm').value=objs['gxqm'];
           document.getElementById('zhmcp').innerHTML=objs['zhmc'];
           document.getElementById('mobile').innerHTML=objs['mobile'];
           document.getElementById('zym').href="Two-dimensional.html?mobile="+objs['mobile'];
          document.getElementById("tx").src=objs['tx'];
           if(objs['sex']=="��"){
        	   document.getElementById("sex").src="./img/boy.png";
           }
           if(objs['sex']=="Ů"){
        	   document.getElementById("sex").src="./img/girl.png";
           }
           if(objs['sex']=="��δ�����Ա�"){
        	   document.getElementById("sex").hidden="hidden";
           }
           
       }, false);
    return;
}
}
//����ǩ��
function qxqm(){
	var data2={};
	data2.gxqm=document.getElementById("gxqm").value;
 	   data2.mobile=document.getElementById("mobile").innerHTML;
	$.ajax({
        url: "ggqm",
        async : true,
        type: "post",
        dataType: "text",
        data:data2,
        success: function (gxqm){
        	document.getElementById('gxqm').value=gxqm;
       
        }
        })
	return;
}
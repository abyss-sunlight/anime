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
	document.getElementById("sj").innerHTML="�ֻ���:"+User.mobile;
}

function scsp(obj){
	var sp=obj.files[0];
	console.log(sp)
	 var objUrl = getObjectURL(sp);
    console.log("objUrl = " + objUrl);
    if (objUrl) {
    	document.getElementById("sp").src= objUrl;
    }
	
}

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
function sc(){
	var file = document.getElementById('file').files[0];
	var filesp = document.getElementById('filesp').files[0];
	var dq=document.getElementById('dq').value;
	var sjian=document.getElementById('sjian').value;
	var jis=document.getElementById('jis').value;
	var jtjs=document.getElementById('jtjs').value;
    var lx=[];
    var name=document.getElementById('name').value;
    if(file==''||file==null){
    	alert("��ѡ��ͼƬ")
    	return;
    }
    if(filesp==''||filesp==null){
    	alert("��ѡ����Ƶ")
    	return;
    }
    var formData = new FormData();
    var imgname = file.name;
    var imgNameFlag = imgname.substring(imgname.indexOf(".")+1,imgname.length);
    console.log(imgNameFlag);
 
    lx[0]="w";
    lx[1]="w";
    lx[2]="w";
    var c=0;
    for(var j=0;j<25;j++){
    	if(document.getElementById(j+1).checked){
    		
    		lx[c]=document.getElementById(j+1).value;
    		c=c+1;
    	}
    	if(c==4){
    		alert("���ѡ��3������")
    		return;
    	}
    	
    	
    }
    if(jtjs==''){
    	alert("������������")
    	return;
    }
    if(dq==''){
    	alert("���������")
    	return;
    }
    if(c==0){
    	alert("��ѡ������һ������")
    	return;
    }
    if(sjian==''){
    	alert("������ʱ��")
    	return;
    }
    var date=/^(\d{4}-\d{2}-\d{2})$/;
	if(!date.test(sjian)){
		alert('ʱ���ʽ����ȷ');
		return;   
	}
    if(jis==''){
    	alert("�����뼯��")
    	return;
    }
    formData.append("imgNameFlag",imgNameFlag);
    formData.append("lx1",lx[0]);
    formData.append("lx2",lx[1]);
    formData.append("lx3",lx[2]);
    formData.append("jtjs",jtjs);
    formData.append("lxs",c);
	formData.append("file",file);
	formData.append("filesp",filesp);	 
	formData.append("name",name);
	formData.append("sjian",sjian);
	formData.append("jis",jis);
	formData.append("dq",dq);
	$.ajax({
		url: 'spsc',
		type: 'POST',
		data: formData, // �ϴ�formdata��װ�����ݰ�
		dataType: 'text',
		cache: false, // ������
		processData: false, // jQuery��Ҫȥ�����͵�����
		contentType: false, // jQuery��Ҫȥ����Content-Type����ͷ
		success: function(data) {
			alert(data);
			document.getElementById('jis').value='';
			document.getElementById('file').value='';
			document.getElementById('filesp').value='';
			document.getElementById('sp').src='#';
			document.getElementById('img').src='./img/kb.png';
			 return;
		}

	});
}
	  
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
	document.getElementById("sj").innerHTML="手机号:"+User.mobile;
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
    	alert("请选择图片")
    	return;
    }
    if(filesp==''||filesp==null){
    	alert("请选择视频")
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
    		alert("最多选择3个类型")
    		return;
    	}
    	
    	
    }
    if(jtjs==''){
    	alert("请输入具体介绍")
    	return;
    }
    if(dq==''){
    	alert("请输入地区")
    	return;
    }
    if(c==0){
    	alert("请选择至少一个类型")
    	return;
    }
    if(sjian==''){
    	alert("请输入时间")
    	return;
    }
    var date=/^(\d{4}-\d{2}-\d{2})$/;
	if(!date.test(sjian)){
		alert('时间格式不正确');
		return;   
	}
    if(jis==''){
    	alert("请输入集数")
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
		data: formData, // 上传formdata封装的数据包
		dataType: 'text',
		cache: false, // 不缓存
		processData: false, // jQuery不要去处理发送的数据
		contentType: false, // jQuery不要去设置Content-Type请求头
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
	  
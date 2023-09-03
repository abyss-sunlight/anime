function ggtx(){
	alert("请选择1:1的图片，图片剪切点击左边的'图片剪切'")
	 document.getElementById("file").click();
}
function ggtx2(){
	alert("请选择2:3的图片'")
	 document.getElementById("file").click();
}
//图片显示
function tpxs(obj){
	var file = obj.files[0];
	 var reader = new FileReader();
	var base64Img;
	 reader.readAsDataURL(file); // 和下面的执行时间要有差距不然base64Img 值生取不出来
	 reader.onloadend = function () {
	        
	        console.log(reader.result);
	    };
     setTimeout(function () {
         base64Img = reader.result;
         document.getElementById("img").src=base64Img;
     },12);
}
//更改头像
function txgg(obj){
	console.log("上传");
	var file = obj.files[0];
    console.log(file)
    var imgname = file.name;
    var mobile=document.getElementById("mobile").innerHTML;
    var imgNameFlag = imgname.substring(imgname.indexOf(".")+1,imgname.length);
    console.log(imgNameFlag);
    var formData = new FormData();
    formData.append("imgNameFlag",imgNameFlag);
	formData.append("file",file);
	formData.append("mobile",mobile);
	$.ajax({
		url: 'sctp',
		type: 'POST',
		data: formData, // 上传formdata封装的数据包
		dataType: 'text',
		cache: false, // 不缓存
		processData: false, // jQuery不要去处理发送的数据
		contentType: false, // jQuery不要去设置Content-Type请求头
		success: function(data) {
			if(data=="上传失败,请联系程序员"){
			alert(data);
			 return;
			}
			alert("上传成功，刷新即可")
			document.getElementById('tx').src=data;
			 return;
		}

	});
}
 

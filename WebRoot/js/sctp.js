function ggtx(){
	alert("��ѡ��1:1��ͼƬ��ͼƬ���е����ߵ�'ͼƬ����'")
	 document.getElementById("file").click();
}
function ggtx2(){
	alert("��ѡ��2:3��ͼƬ'")
	 document.getElementById("file").click();
}
//ͼƬ��ʾ
function tpxs(obj){
	var file = obj.files[0];
	 var reader = new FileReader();
	var base64Img;
	 reader.readAsDataURL(file); // �������ִ��ʱ��Ҫ�в�಻Ȼbase64Img ֵ��ȡ������
	 reader.onloadend = function () {
	        
	        console.log(reader.result);
	    };
     setTimeout(function () {
         base64Img = reader.result;
         document.getElementById("img").src=base64Img;
     },12);
}
//����ͷ��
function txgg(obj){
	console.log("�ϴ�");
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
		data: formData, // �ϴ�formdata��װ�����ݰ�
		dataType: 'text',
		cache: false, // ������
		processData: false, // jQuery��Ҫȥ�����͵�����
		contentType: false, // jQuery��Ҫȥ����Content-Type����ͷ
		success: function(data) {
			if(data=="�ϴ�ʧ��,����ϵ����Ա"){
			alert(data);
			 return;
			}
			alert("�ϴ��ɹ���ˢ�¼���")
			document.getElementById('tx').src=data;
			 return;
		}

	});
}
 

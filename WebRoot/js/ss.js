function ss(){
	var ss=document.getElementById("ss").value;
	var mobile=document.getElementById("mobile").innerHTML; 
	window.open(encodeURI('ss.html?ss='+ss+'&mobile='+mobile)); 
}
function body2(){
	var search=decodeURI(location.href);
	function parse(search){
	    //�ӵڶ����ַ���ʼ��ȡ����ȡ���ڶ�����ʼ�������е��ַ�
		 
	    var str= search.substring(search.indexOf("?")+1,search.length);
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
	console.log(User)
	var a="upjm.html?mobile=";
	var d="&lx=0";
	var a1=a+User.mobile+d;
	var y=0;
	$.ajax({
        url: "ss",
        async : true,
        type: "post",
        dataType: "text",
       
        data:User,
        success: function (obj){
      	  var objs =  JSON.parse(obj);
      	document.getElementById("li1m").src=objs['tx'];
		document.getElementById("li1s").hidden="hidden";
		document.getElementById("li1m").removeAttribute("hidden");
		document.getElementById("a1").href=a1;
		document.getElementById("zy").href='Two-dimensional.html?mobile='+User.mobile;
		document.getElementById("a2").href=a1;
		document.getElementById("a2").innerHTML=objs['zhmc'];
		document.getElementById("mobile").innerHTML=User.mobile;
		
		if(objs['jgsl']=="���޽��"){
			document.getElementById("bt").innerHTML='��������:'+User.ss+'------���޽��';
			return;
		}
		document.getElementById("bt").innerHTML='��������:'+User.ss;
		var jgsl=objs['jgsl'];
		console.log(jgsl)
		for(var t=0;t<jgsl;t++){
			j=t+1;
			var li=document.createElement("li");
			var a1 = document.createElement("a");
			var br = document.createElement("br");
			var a2 = document.createElement("a");
			var a3 = document.createElement("a");
			var img1=document.createElement("img");
			var img2=document.createElement("img");
			var src2=document.createAttribute('src');
			var jiss = document.getElementById("jg");
			src2.value='./sp/bj.png';
			var d=document.createAttribute('class');
			d.value='bf';
			var target1=document.createAttribute('target');
			target1.value='_blank';
			var target3=document.createAttribute('target');
			target3.value='_blank';
			var target2=document.createAttribute('target');
			target2.value='_blank';
			var src1=document.createAttribute('src');
			src1.value=objs['tp'+j];
			
			var ap2=document.createTextNode(objs['name'+j]);
			var ap3 = document.createTextNode("����:"+objs['jis'+j]+"��");
			var href1=document.createAttribute('href')
			href1.value='transmit.html?id='+objs['id'+j]+'&mobile='+User.mobile;
			var href2=document.createAttribute('href')
			href2.value='transmit.html?id='+objs['id'+j]+'&mobile='+User.mobile;
			var href3=document.createAttribute('href')
			href3.value='transmit.html?id='+objs['id'+j]+'&mobile='+User.mobile;
			if(t%5==0){
  				
     			 y=y+400;
     				document.getElementById("nr").style.height=y+'px';
     			}
			
			a1.setAttributeNode(target1)
			a2.setAttributeNode(target2)
			a3.setAttributeNode(target3)
			a1.setAttributeNode(href1)
			a2.setAttributeNode(href2)
			a3.setAttributeNode(href3)
			img1.setAttributeNode(src1)
			img2.setAttributeNode(src2)
			img2.setAttributeNode(d)
			a2.appendChild(ap2);
			a3.appendChild(ap3);
			a1.appendChild(img1);
			a1.appendChild(img2);
			li.appendChild(a1);
			li.appendChild(a2);
			li.appendChild(br);
			li.appendChild(a3);
			jiss.appendChild(li);
		}
		document.getElementsByTagName("title")[0].innerText ='����'+User.ss;
		return;
        }
        })
}

function leix(lxbt){
	
	var mobile=document.getElementById("mobile").innerHTML; 
	var lx=lxbt.innerHTML;
	document.getElementById('content').src=encodeURI('./conentlx.html?mobile='+mobile+'&lx='+lx);
	//var data='{ "lx":"'+lx+'","mobile":"'+mobile+'"}';
	//document.getElementById('content').contentWindow.postMessage(lx, "http://localhost:8080");
	
}
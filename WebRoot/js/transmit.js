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
	var a="upjm.html?mobile=";
	var d="&lx=0";
	var a1=a+User.mobile+d;
	$.ajax({
        url: "transmit",
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
		document.getElementById("jtjs").innerHTML=objs['jtjs'];
		document.getElementById("name").innerHTML=objs['name'];
		document.getElementsByTagName("title")[0].innerText = objs['name'];
		document.getElementById("dq").innerHTML="����:"+objs['dq'];
		document.getElementById("sjian").innerHTML="���:"+objs['sjian'];
		document.getElementById("lx").innerHTML="����:"+objs['lx'];
		document.getElementById("sp").src=objs['sp'];
		document.getElementById("zxjis").innerHTML="������:"+objs['jis'];
		var jis=objs['jis']
		
		
		for(var t=0;t<jis;t++){
			j=t+1;
			var para = document.createElement("button");
			var node = document.createTextNode("��"+j+"��");
			var sj3=document.createAttribute('onclick')
			var jiss = document.getElementById("jis");
			var value=document.createAttribute('value')
			value.value=User.id+'-'+j
			sj3.value='hj(this)';
			para.setAttributeNode(value)
			para.setAttributeNode(sj3)
			para.appendChild(node);
			jiss.appendChild(para);
		}
        }
        })
}
function hj(jiss){
	var jis=jiss.value;
	document.getElementById("sp").src='./spmp4/'+jis+'.mp4';
	var dj=jis.substring(jis.indexOf("-")+1,jis.length);
	document.getElementById("djis").innerHTML="��"+dj+"��";
}

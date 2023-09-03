
//获取信息
function parse(search){
	    //从第二个字符开始截取，获取到第二个开始后面所有的字符
	    var str=search.substring(1);
	    if(str==''){
	    	var wu="w"
	    	return wu;
	    }
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
//开头判断
window.onload= function (){
	
	var search=location.search;
	var User = parse(search);
	if(User=='w'){
		alert("请先登录或注册")
		window.location.href ='Two-dimensional.html';
	}
	const iframe = document.getElementById('upnr');
	if(User.lx=="0"){
	 //传递数据到子页面
       iframe.contentWindow.postMessage(User.mobile, "http://localhost:8080");
       
       window.addEventListener('message', function(event) {
    	   var objs =  JSON.parse(event.data);
           document.getElementById('gxqm').value=objs['gxqm'];
           document.getElementById('zhmcp').innerHTML=objs['zhmc'];
           document.getElementById('mobile').innerHTML=objs['mobile'];
           document.getElementById('zym').href="Two-dimensional.html?mobile="+objs['mobile'];
          document.getElementById("tx").src=objs['tx'];
           if(objs['sex']=="男"){
        	   document.getElementById("sex").src="./img/boy.png";
           }
           if(objs['sex']=="女"){
        	   document.getElementById("sex").src="./img/girl.png";
           }
           if(objs['sex']=="还未设置性别"){
        	   document.getElementById("sex").hidden="hidden";
           }
           
       }, false);
    return;
}
}
//更改签名
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
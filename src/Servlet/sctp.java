package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javaBean.spbean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import Dao.scspdao;
import Dao.sctpdao;

public class sctp extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public sctp() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入sctp图片上传控制层");
		 try {
		//设置缓冲区大小
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    //创建一个上床据
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    //设置上传文件大小
	    upload.setFileSizeMax(1024*1024*10000);
	    upload.setSizeMax(1024*1024*10000);
	    //解决上传文件中文名称乱码
	    upload.setHeaderEncoding("UTF-8");
	    //定义一个map接收普通表单对象
	    Map<String, String> param = new HashMap<String, String>();
	    //定义一个List接受文件数据
	    List<FileItem> iter = new ArrayList<FileItem>();

	    //解析request对象，得到所有上传项，每一个fileItem相当于一个上传项（包括普通表单数据和文件数据）
	    List<FileItem> items;
		items = upload.parseRequest(request);

	   
	        //遍历解析出来的对象
	        for (FileItem fileItem : items) {
	            if (fileItem.isFormField()) {//如果是普通输入项数据
	                param.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
	            }else {//如果是上传的文件数据
	                iter.add(fileItem);
	            }
	        }
	    
	    //将我们获取到的普通表单值赋值出来（之前我们存在了map里面）
	    String imgIndex=param.get("imgNameFlag");
	    String mobile=param.get("mobile");
	    	
	   
	       
	    
	    //如果请求中存在文件
	    //如果不存在文件那么iter.get(0).getName()
	    if(!StringUtils.isEmpty(iter.get(0).getName())){
	        
	        //开始文件操作
	        //创建文件数据集合的迭代器
	        Iterator<FileItem> iterator = iter.iterator();

	        //如果上传了多个文件，则会执行多次
	        while(iterator.hasNext()){
	            //获取迭代器中数据（存储的是我们传上来的文件数据）
	            FileItem fileItem = iterator.next();
	            //获取文件输入流
	            String filetype = fileItem.getContentType();
	            	File targetFile = new File("..\\webapps\\ecywz\\tx",mobile+"."+imgIndex);
		            fileItem.write(targetFile);
	            
	            
	            //获取文件名
	            String fileName = fileItem.getName();
	            System.out.print(fileName);
	            //后面的就是可以对文件输入流进行相关操作了
	            
	            //以下省略

	        }
	    
	    }
	    sctpdao sp=new sctpdao();
 String htmlpath="./tx/"+mobile+"."+imgIndex;
	    int t=sp.ggtx(mobile, htmlpath);
	   if(t==1){
		   renderData(response,htmlpath);
	   }
	   if(t==0){
		   renderData(response,"上传失败,请联系程序员");
	   }
		 } catch (Exception e) {
		        e.printStackTrace();
		    }  
	    
	}
	protected void renderData(HttpServletResponse response, String data){
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

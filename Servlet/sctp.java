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
		System.out.println("����sctpͼƬ�ϴ����Ʋ�");
		 try {
		//���û�������С
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    //����һ���ϴ���
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    //�����ϴ��ļ���С
	    upload.setFileSizeMax(1024*1024*10000);
	    upload.setSizeMax(1024*1024*10000);
	    //����ϴ��ļ�������������
	    upload.setHeaderEncoding("UTF-8");
	    //����һ��map������ͨ������
	    Map<String, String> param = new HashMap<String, String>();
	    //����һ��List�����ļ�����
	    List<FileItem> iter = new ArrayList<FileItem>();

	    //����request���󣬵õ������ϴ��ÿһ��fileItem�൱��һ���ϴ��������ͨ�����ݺ��ļ����ݣ�
	    List<FileItem> items;
		items = upload.parseRequest(request);

	   
	        //�������������Ķ���
	        for (FileItem fileItem : items) {
	            if (fileItem.isFormField()) {//�������ͨ����������
	                param.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
	            }else {//������ϴ����ļ�����
	                iter.add(fileItem);
	            }
	        }
	    
	    //�����ǻ�ȡ������ͨ��ֵ��ֵ������֮ǰ���Ǵ�����map���棩
	    String imgIndex=param.get("imgNameFlag");
	    String mobile=param.get("mobile");
	    	
	   
	       
	    
	    //��������д����ļ�
	    //����������ļ���ôiter.get(0).getName()
	    if(!StringUtils.isEmpty(iter.get(0).getName())){
	        
	        //��ʼ�ļ�����
	        //�����ļ����ݼ��ϵĵ�����
	        Iterator<FileItem> iterator = iter.iterator();

	        //����ϴ��˶���ļ������ִ�ж��
	        while(iterator.hasNext()){
	            //��ȡ�����������ݣ��洢�������Ǵ��������ļ����ݣ�
	            FileItem fileItem = iterator.next();
	            //��ȡ�ļ�������
	            String filetype = fileItem.getContentType();
	            	File targetFile = new File("..\\webapps\\ecywz\\tx",mobile+"."+imgIndex);
		            fileItem.write(targetFile);
	            
	            
	            //��ȡ�ļ���
	            String fileName = fileItem.getName();
	            System.out.print(fileName);
	            //����ľ��ǿ��Զ��ļ�������������ز�����
	            
	            //����ʡ��

	        }
	    
	    }
	    sctpdao sp=new sctpdao();
 String htmlpath="./tx/"+mobile+"."+imgIndex;
	    int t=sp.ggtx(mobile, htmlpath);
	   if(t==1){
		   renderData(response,htmlpath);
	   }
	   if(t==0){
		   renderData(response,"�ϴ�ʧ��,����ϵ����Ա");
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

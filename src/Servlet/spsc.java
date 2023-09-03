package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import Dao.scspdao;
import Dao.sctpdao;



public class spsc extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public spsc() {
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
	public void doPost( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����spsc��Ƶ�ϴ����Ʋ�");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
	    
	    spbean sb=new spbean();
	    //�����ǻ�ȡ������ͨ��ֵ��ֵ������֮ǰ���Ǵ�����map���棩
	    String imgIndex=param.get("imgNameFlag");
	    String lx1 = null;
    	String lx2 =null;
    	String lx3 =null;
	    String name =  param.get("name");
	    String dq =  param.get("dq");
	    String sjian =  param.get("sjian");
	    String jis =  param.get("jis");
	    String jtjs=param.get("jtjs");
	    int lxs =  Integer.parseInt(param.get("lxs"));
	    if(lxs==3){
	    	 lx1 =  param.get("lx1");
	     lx2 =  param.get("lx2");
	    lx3 =  param.get("lx3");
	    }
	    if(lxs==2){
	    	 lx1 =  param.get("lx1");
	    	 lx2 =  param.get("lx2");
	    }
	    if(lxs==1){
	    	 lx1 =  param.get("lx1");
	    }
	    String htmlpath=null;
	    String tppath=null;
	    sb.setDq(dq);
	    sb.setJis(jis);
	    sb.setLx(lxs);
	    sb.setLx1(lx1);
	    sb.setLx2(lx2);
	    sb.setLx3(lx3);
	    sb.setName(name);
	    sb.setJtjs(jtjs);
	    sb.setSjian(simpleDateFormat.parse(sjian));
	    scspdao sd=new scspdao();
	    int id=sd.spmlcx(name,sb);
	    System.out.println("5"+id);
	    	
	   
	       
	    
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
	            if(filetype.equals("video/mp4")){
	            File targetFile = new File("..\\webapps\\ecywz\\spmp4",id+"-"+jis+".mp4");
	            fileItem.write(targetFile);
	            }else{
	            	File targetFile = new File("..\\webapps\\ecywz\\sp",id+"."+imgIndex);
		            fileItem.write(targetFile);
	            }
	           
	            
	            //��ȡ�ļ���
	            String fileName = fileItem.getName();
	            System.out.print(fileName);
	            //����ľ��ǿ��Զ��ļ�������������ز�����
	            
	            //����ʡ��

	        }
	    
	    }
 htmlpath="./sp/"+id+"."+imgIndex;
	    sb.setTp(htmlpath);
	   String sppath="./spmp4/"+id+"-"+jis+".mp4";
	   sb.setSp(sppath);
	   int t=sd.cjzykml(id, sb);
	   if(t==1){
		   renderData(response,"�ϴ��ɹ�");
	   }
	   if(t==2){
		   renderData(response,"�����ظ����������ϴ�");
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

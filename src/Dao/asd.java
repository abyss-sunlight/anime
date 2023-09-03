package Dao;

import java.io.File;


public class asd {
	public static  void insert(String b) {
		//1.创建文件夹对象
				File fl=new File("img");//默认在该项目里
				
				if(!fl.exists()){
					fl.mkdir();//创建目录（文件夹）
					System.out.println("创建成功");
				}
				System.out.println("创建2");
		
	}
	
	public static void main(String[] args){
		String b="c";
		insert(b);
}}

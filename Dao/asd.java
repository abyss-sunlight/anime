package Dao;

import java.io.File;


public class asd {
	public static  void insert(String b) {
		//1.�����ļ��ж���
				File fl=new File("img");//Ĭ���ڸ���Ŀ��
				
				if(!fl.exists()){
					fl.mkdir();//����Ŀ¼���ļ��У�
					System.out.println("�����ɹ�");
				}
				System.out.println("����2");
		
	}
	
	public static void main(String[] args){
		String b="c";
		insert(b);
}}

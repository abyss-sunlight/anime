package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class zhuanhuan {
	//ͼƬת�ֽ���
		public FileInputStream tpzh(String path){
			//ͼƬ·��
			FileInputStream in = null;
	        File file = new File(path);
	        
	        
	        try {
	            //��fileתΪ������
	            in = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
		return in;
		}
}

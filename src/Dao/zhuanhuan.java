package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class zhuanhuan {
	//图片转字节流
		public FileInputStream tpzh(String path){
			//图片路径
			FileInputStream in = null;
	        File file = new File(path);
	        
	        
	        try {
	            //将file转为输入流
	            in = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
		return in;
		}
}

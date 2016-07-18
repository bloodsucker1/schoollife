package com.example.work.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e(e);
			}
		}
		return true;
	}
	//拷贝资源文件
		/*1.打开资产目录的文件     
		 *2.获取输入流	
		 *3.在手机本地目录创建文件
		 *由于：每个apk安装之后只能访问/data/data/自己包名/下面的私有空间
		 */
		public static void CopyAssetsToFiles(String Path,String assetsPath,Context context){
			File file = new File(Path);
			LogUtils.e("准备拷贝");
			if(!(file.exists())){//如果文件不存在才创建
				AssetManager manager = context.getAssets();//获取资源文件的管理目录
				try {
					InputStream in = manager.open(assetsPath);
					FileOutputStream out = new FileOutputStream(file);
					
					byte[] b= new byte[1024];
					int len =-1;
					while((len =in.read(b))!=-1){
						out.write(b, 0, len);
					}
					out.close();//关闭流
					in.close();
					LogUtils.e("拷贝成功");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//读取文件为字符串
		public static String readFile(String path){
			 String str="";
			 File file=new File(path);
			 try {
			     FileInputStream in=new FileInputStream(file);
			     // size  为字串的长度 ，这里一次性读完
			     int size=in.available();
			     byte[] buffer=new byte[size];
			     in.read(buffer);
	            in.close();
			     str=new String(buffer,"UTF-8");
			    } catch (IOException e) {
			    	e.printStackTrace();
			        return null;
			    }
			 return str;
		}
}

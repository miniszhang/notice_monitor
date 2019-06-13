package com.apicloud.notice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.os.Environment;


public class Public {
	public static String getFile(String fileName) {
        try {
            // 创建文件
            File file = new File(Environment.getExternalStorageDirectory(),fileName);
            if(!file.exists()){
            	saveFile("",fileName);
            }
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建字节数组 每次缓冲1M
            byte[] b = new byte[1024];
            int len = 0;// 一次读取1024字节大小，没有数据后返回-1.
            // 创建ByteArrayOutputStream对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 一次读取1024个字节，然后往字符输出流中写读取的字节数
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            // 将读取的字节总数生成字节数组
            byte[] data = baos.toByteArray();
            // 关闭字节输出流
            baos.close();
            // 关闭文件输入流
            fis.close();
            // 返回字符串对象
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public static void saveFile(String str, String fileName) {
        // 创建String对象保存文件名路径
        try {
            // 创建指定路径的文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            // 如果文件不存在
            if (file.exists()) {
                // 创建新的空文件
                file.delete();
            }
            file.createNewFile();
            // 获取文件的输出流对象
            FileOutputStream outStream = new FileOutputStream(file);
            // 获取字符串对象的byte数组并写入文件流
            outStream.write(str.getBytes());
            // 最后关闭文件输出流
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void deletefile(String fileName) {
        try {
        	
            // 找到文件所在的路径并删除该文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            if(!file.exists()){
            	file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

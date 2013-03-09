package org.ape.data.core.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @title
 * @description
 * @usage
 * @author yushuanghai
 * @version $Id: JavaShellUtil.java,v 1.3 2013-3-7 上午10:16:54 $
 * @create 2013-3-7 上午10:16:54
 */
public class JavaShellUtil {
	// 基本路径
	private static final String basePath = "/tmp/";
	public int executeShell(String shellCommand,String logName) throws IOException {  
		StringBuffer stringBuffer = new StringBuffer();  
		BufferedReader bufferedReader = null;  
	    int success = 0;  
     
		//格式化日期时间，记录日志时使用  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");  
		try {  
		stringBuffer.append(dateFormat.format(new Date())).append("ready execute Shell command ").append(shellCommand).append(" \r\n");  
		Process pid = null;  
		String[] cmd = {"/bin/sh", "-c", shellCommand};  
		//执行Shell命令  
		pid = Runtime.getRuntime().exec(cmd);  
		if (pid != null) {  
		stringBuffer.append("pid：").append(pid.toString()).append("\r\n");  
		//bufferedReader用于读取Shell的输出内容 bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);  
		pid.waitFor();  
		}else{  
		stringBuffer.append("no pid\r\n");  
		}  
        stringBuffer.append(dateFormat.format(new Date())).append("Shell command finished \r\nresult：\r\n");  
        String line = null;  
		//读取Shell的输出内容，并添加到stringBuffer中  
		while (bufferedReader != null&&(line = bufferedReader.readLine()) != null) {  
			stringBuffer.append(line).append("\r\n");  
		}  
		
		} catch (Exception ioe) {  
		stringBuffer.append("Shell command execute exception：\r\n").append(ioe.getMessage()).append("\r\n");  
		} finally {  
			if (bufferedReader != null) {  
			OutputStreamWriter outputStreamWriter = null;  
			try {  
			bufferedReader.close();  
			//将Shell的执行情况输出到日志文件中  
			OutputStream outputStream = new FileOutputStream(basePath + logName);  
			outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");  
			outputStreamWriter.write(stringBuffer.toString());  
			} catch (Exception e) {  
			e.printStackTrace();  
			} finally {  
			outputStreamWriter.close();  
			}  
		}  
		success = 1;  
		}  
		return success;  
    }
	
}
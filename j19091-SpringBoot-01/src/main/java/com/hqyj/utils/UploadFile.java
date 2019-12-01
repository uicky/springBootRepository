package com.hqyj.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFile {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFile.class);

	public static boolean  uploadFile(byte[] file, String filePath, String fileName) {
		boolean flag = true;// 默认上传成功
		File targetFile = new File(filePath);
		// 如果当前文件目录不存在即U自动创建该文件或目录
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName);
			fileOutputStream.write(file);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			LOGGER.error( "---文件找不到异常----",e);
			flag = false;
		} catch (IOException e) {
			LOGGER.error( "----IO异常----",e);
			flag = false;
		}
		return flag;
	}

}

package com.itsync.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class CodeUtils {
	public static boolean GenerateImage(String imgData, String imgFilePath) {
		// 对字节数组字符串进行Base64解码并生成图片
		if (imgData == null)
			return false;
		Decoder decoder = Base64.getDecoder();

		// 解密
		byte[] b = decoder.decode(imgData);
		// 处理数据
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}
		OutputStream out;
		try {
			out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}
}

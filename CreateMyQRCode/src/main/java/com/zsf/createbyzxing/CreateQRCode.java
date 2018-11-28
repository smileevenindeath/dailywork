package com.zsf.createbyzxing;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * @ClassName: CreateQRCode.java
 * @Description: 生成二维码 QRCode
 * @author 周生锋
 * @version V1.0
 * @Date 2018年11月28日 上午11:29:21
 */
public class CreateQRCode {

	public static void createQRCode(String content) {
		// 定义图片的 宽度 高度
		int width = 300;
		int height = 300;

		// 定义图片的格式
		String format = "png";

		// 定义图片的内容
		//String content = "http://www.baidu.com";

		// 定义二维码的参数
		HashMap hints = new HashMap<>();
		// 设置编码集
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// 设置容错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// 设置边距
		hints.put(EncodeHintType.MARGIN, 2);

		// 生成二维码
		try {
			BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			Path file = new File("D:/myQRCode.png").toPath();
			MatrixToImageWriter.writeToPath(encode, format, file);
			System.out.println("二维码生成成功！");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

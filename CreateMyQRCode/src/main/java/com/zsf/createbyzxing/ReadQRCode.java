package com.zsf.createbyzxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * 
 * @ClassName: ReadQRCode.java
 * @Description: 读取二维码
 * @author 周生锋
 * @version V1.0
 * @Date 2018年11月28日 下午1:58:06
 */
public class ReadQRCode {

	public static void readQRCode() {
		try {
			MultiFormatReader reader = new MultiFormatReader();
			File image = new File("D:/MyQRCode.png");
			BufferedImage bImagege = ImageIO.read(image);
			BinaryBitmap binaryBitmap = new BinaryBitmap(
					new HybridBinarizer(new BufferedImageLuminanceSource(bImagege)));
			// 定义二维码的参数
			HashMap hints = new HashMap<>();
			// 设置编码集
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

			Result result = reader.decode(binaryBitmap, hints);

			System.out.println("解析结果： " + result.toString());
			System.out.println("二维码类型： " + result.getBarcodeFormat());
			System.out.println("解析文本内容： " + result.getText());

		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

}

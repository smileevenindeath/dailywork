package com.zsf.createbyzxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCodeByZXingImpl implements CreateQRCodeByZXing {

	@Override
	public void createQRCode(String content, String path) throws Exception {
		// 定义图片的 宽度 高度
		int width = 300;
		int height = 300;
		// 定义图片的格式
		String format = "png";
		// 定义图片的内容
		// String content = "http://www.baidu.com";
		// 定义二维码的参数
		HashMap hints = new HashMap<>();
		// 设置编码集
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// 设置容错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// 设置边距
		hints.put(EncodeHintType.MARGIN, 2);
		// 生成二维码

		BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		Path file = new File(path).toPath();
		MatrixToImageWriter.writeToPath(encode, format, file);
		System.out.println("二维码生成成功！");

	}

	@Override
	public void readQRCode(String path) throws Exception {

		MultiFormatReader reader = new MultiFormatReader();
		File image = new File(path);
		BufferedImage bImagege = ImageIO.read(image);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bImagege)));
		// 定义二维码的参数
		HashMap hints = new HashMap<>();
		// 设置编码集
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

		Result result = reader.decode(binaryBitmap, hints);

		System.out.println("解析结果： " + result.toString());
		System.out.println("二维码类型： " + result.getBarcodeFormat());
		System.out.println("解析文本内容： " + result.getText());

	}

}

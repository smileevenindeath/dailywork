package com.zsf.createbyqrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class CreateQRCodeByQRCodeImpl implements CreateQRCodeByQRCode {

	@Override
	public void createQRCode(String content, String savepath, Integer version) throws Exception {
		Qrcode xCode = new Qrcode();
		xCode.setQrcodeErrorCorrect('M');// 纠错等级
		xCode.setQrcodeEncodeMode('B');// n 代表数字 a代表 a-z b 代表任意字符
		xCode.setQrcodeVersion(version);// 版本

		// 创建缓冲器图片 公式 67 + 12 * (version - 1)
		Integer width = 67 + 12 * (version - 1);
		Integer height = 67 + 12 * (version - 1);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 使用java 画图工具
		Graphics2D gs = image.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, width, height);

		// 添加偏移量 不添加是可能导致解析失败
		Integer pixoff = 2;

		byte[] bs = content.getBytes("gb2312");
		if (bs.length > 0 && bs.length < 120) {
			boolean[][] s = xCode.calQrcode(bs);
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[j][i]) {
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		}
		gs.dispose();
		image.flush();
		ImageIO.write(image, "png", new File(savepath));
		System.out.println("二维码生成成功");

	}

	@Override
	public void readQRCode(String path) throws Exception {
		File file = new File(path);
		BufferedImage read = ImageIO.read(file);
		QRCodeDecoder codeDecoder = new QRCodeDecoder();
		String result = new String(codeDecoder.decode(new MyQRCodeDeCode(read)), "gb2312");
		System.out.println("解析结果:  " + result.toString());
	}

	@Override
	public void createQRCode(String content) throws Exception {
		createQRCode(content, "D:/moren.png", 7);
	}

	@Override
	public void createQRCode(String content, String savepath) throws Exception {
		createQRCode(content, savepath, 7);

	}

	@Override
	public void readQRCode() throws Exception {
		readQRCode("D:/moren.png");

	}

}

package com.zsf.createbyzxing;

public interface CreateQRCodeByZXing {

	/**
	 * 
	 * @Title: createQRCode
	 * @Description: 生成二维码
	 * @param content 二维码内容 path 存储路径
	 * @throws Exception
	 * @author 周生锋
	 * @Date 2018年11月28日 下午2:51:00
	 */
	public void createQRCode(String content, String path) throws Exception;

	/**
	 * 
	 * @Title: readQRCode
	 * @Description:读取二维码
	 * @param path 读取路径
	 * @throws Exception
	 * @author 周生锋
	 * @Date 2018年11月28日 下午2:56:56
	 */
	public void readQRCode(String path) throws Exception;

}

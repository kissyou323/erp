package com.erp.util;

//import java.io.File;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;

public class QrCodeUtil {

	/*
	 * 编码： 1 将内容转换成二维矩阵 2 将该二维矩阵转换成图片
	 */
//	public static void encode(String str, String path) throws Exception {
//		// String str = "http://www.baidu.com百度看看";// 二维码内容
//		// String path = "D:/Qr_pics/test7.png"; //二维码图片生成 路径及名称
//		BitMatrix byteMatrix;
//		byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("UTF-8"), "ISO-8859-1"),BarcodeFormat.QR_CODE, 180, 180); // 将文字转换成二维矩阵，并设置矩阵大小，这里的矩阵大小就是后面生成的图片像素大小
//		File file = new File(path);// 新建矩阵文件
//		MatrixToImageWriter.writeToFile(byteMatrix, "gif", file);// 将矩阵文件转换成图片文件
//	}
//	public static void main(String[] args) {
//	  String str = "http://www.baidu.com百度看看";// 二维码内容
//         String path = "D:/test7.png"; //二维码图片生成 路径及名称
//	    try {
//            encode(str,path);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}

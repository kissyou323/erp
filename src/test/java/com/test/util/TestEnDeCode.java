package com.test.util;

//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Hashtable;
//import java.util.Scanner;
//import javax.imageio.ImageIO;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.DecodeHintType;
//import com.google.zxing.LuminanceSource;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.Reader;
//import com.google.zxing.ReaderException;
//import com.google.zxing.Result;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.HybridBinarizer;

public class TestEnDeCode {

//	/** 
// *  
// */
//	public TestEnDeCode() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		TestEnDeCode t = new TestEnDeCode();
//		Scanner in = new Scanner(System.in);
//		System.out.println("编码内容：");
////		String str = in.next();
//		 String str = "装备信息 [装备编号=2323, 装备名称=2332, 规格类型=3223, 装备说明=232332, 保管员=323232, 状态=完好]";
//		String path = "D:\\dev\\apache-tomcat-8.0.32\\webapps\\erp\\upload\\qrcode\\2323.jpg";
//		t.encode(str, path);
////		t.decode(path);
//	}
//
//	/*
//	 * 编码： 1 将内容转换成二维矩阵 2 将该二维矩阵转换成图片
//	 */
//	public void encode(String str, String path) {
//		try {
//			// String str = "http://www.baidu.com百度看看";// 二维码内容
//			// String path = "D:/Qr_pics/test7.png"; //二维码图片生成 路径及名称
//			BitMatrix byteMatrix;
//			byteMatrix = new MultiFormatWriter().encode(
//					new String(str.getBytes("UTF-8"), "ISO-8859-1"),
//					BarcodeFormat.QR_CODE, 800, 800); // 将文字转换成二维矩阵，并设置矩阵大小，这里的矩阵大小就是后面生成的图片像素大小
//			File file = new File(path);// 新建矩阵文件
//			MatrixToImageWriter.writeToFile(byteMatrix, "gif", file);// 将矩阵文件转换成图片文件
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/*
//	 * 解码： 1 将图片反解码为二维矩阵 2 将该二维矩阵解码为内容
//	 */
//	public void decode(String imgPath) {
//		try {
//			Reader reader = new MultiFormatReader();
//			// String imgPath = "D:/Qr_pics/test7.png";//获取即将被解码图片的路径
//			File file = new File(imgPath);// 获取该图片文件
//			BufferedImage image;
//			try {
//				image = ImageIO.read(file);
//				if (image == null) {
//					System.out.println("Could not decode image");
//				}
//				LuminanceSource source = new BufferedImageLuminanceSource(image);
//				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//						source));
//				Result result;
//				Hashtable hints = new Hashtable();// 将图片反解码为二维矩阵
//				hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
//				result = new MultiFormatReader().decode(bitmap, hints);// 将该二维矩阵解码成内容
//				String resultStr = result.getText();
//				System.out.println("\n解码结果：\n" + resultStr);
//
//			} catch (IOException ioe) {
//				System.out.println(ioe.toString());
//			} catch (ReaderException re) {
//				System.out.println(re.toString());
//			}
//
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//		}
//	}

}
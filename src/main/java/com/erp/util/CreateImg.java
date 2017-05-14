package com.erp.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import com.erp.util.date.DateUtil;
import com.swetake.util.Qrcode;

public class CreateImg {

	/**
	 * 生成二维码
	 * 
	 * @param fileUrl
	 *            二维码图片存在的位置及名字，例如:d:/file/1.png
	 * @param content
	 * 
	 * @return
	 */
	public static String create(String fileUrl, String content) {
		CreateImg handler = new CreateImg();
		File file = new File(fileUrl);
		int num = fileUrl.lastIndexOf(".");
		String suffix = fileUrl.substring((num + 1), fileUrl.length());
		try {
			if (file.exists()) {// 判断文件目录的存在
				if (file.isDirectory()) {// 判断文件的存在性
				} else {
					file.createNewFile();// 创建文件
				}
			} else {
				File file2 = new File(file.getParent());
				file2.mkdirs();
				if (file.isDirectory()) {
				} else {
					file.createNewFile();// 创建文件
				}
			}
			OutputStream output = new FileOutputStream(fileUrl);
			handler.encoderQRCode(content, output, suffix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void encoderQRCode(String content, OutputStream output, String suffix) {
		this.encoderQRCode(content, output, suffix, 8);
	}

	public void encoderQRCode(String content, OutputStream output,
			String imgType, int size) {
		System.out.println("size:"+size);
		try { // size 是文件的大小，取值范围在1-40
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(size);
			byte[] contentBytes = content.getBytes("utf-8");
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);
			gs.setColor(Color.BLACK);
			int pixoff = 2;
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = "
						+ contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	public static void main(String[] args) {
//		String imgUrl = "e:/1.png";
//		String context = "11";
//		CreateImg s = new CreateImg();
//		s.create(imgUrl, context);
	    String str = "1439172802";
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    String a = formatter.format(DateUtil.parse(str,DateUtil.YYYYMMDDHHMMSS));
	    System.out.println(a);
	}
}

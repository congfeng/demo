/**
 * 
 */
package com.cf.code.test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class ZxingTest {

	public static void main(String[] args) throws Exception {
//		qrcodeCreator();
//	    System.out.println(qrcodeReader("E:\\workspace\\_resources\\dc.png"));
	    System.out.println(qrcodeReader("E:\\workspace\\_resources\\lALOaisi6M0BkM0BkA_400_400.png"));
	}
	
	public static void qrcodeCreator() throws Exception{
        Path path = FileSystems.getDefault().getPath("E:\\workspace\\jisheng_workspace\\_resources\\zxing", "zxing.png");
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); 
        BitMatrix bitMatrix = new MultiFormatWriter().encode("http://192.168.30.155:8005/front/productinfo.html?id=1",BarcodeFormat.QR_CODE, 150, 150,hints);
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);// 输出图像  
	}
	
	public static String qrcodeReader(String qrcodePath) throws Exception{
	    BufferedImage img = ImageIO.read(new FileInputStream(qrcodePath)); 
	    LuminanceSource source = new BufferedImageLuminanceSource(img);
	    Binarizer binarizer =  new HybridBinarizer(source);
	    BinaryBitmap image = new BinaryBitmap(binarizer);
	    Result result = new MultiFormatReader().decode(image);
	    return result.toString();
	}
	
}

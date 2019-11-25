package com.example.demo.demoboot2.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @Auther: lichangtong
 * @Date: 2019-11-25 11:56
 * @Description:
 */
public class MatrixToImageWriterUtil {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriterUtil() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "
                    + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static void main(String[] args) throws Exception {
        String text = "http://www.baidu.com?wd=网易邮箱qq邮箱登录&rsv_spt=1&rsv_iqid=0x966e9444002bd3eb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=monline_3_dg&rsv_enter=1&rsv_dl=tb&oq=java%2520%25E7%2594%259F%25E6%2588%2590%25E4%25BA%258C%25E7%25BB%25B4%25E7%25A0%2581%25E5%259B%25BE%25E7%2589%2587&sug=%25E7%25BD%2591%25E6%2598%2593%25E9%2582%25AE%25E7%25AE%25B1qq%25E9%2582%25AE%25E7%25AE%25B1%25E7%2599%25BB%25E5%25BD%2595&inputT=11715&rsv_t=106fLLvUWQlvuzXi3USwX%2Bzaww%2BEMU3HDMz7K86RP89KL0fFJWLNOO48GzfssGCrmS4I&rsv_pq=ce958d1d0000a150&rsv_sug3=153&rsv_sug1=65&rsv_sug7=100&rsv_n=1&bs=java 生成二维码图片"; // 二维码内容
        int width = 300; // 二维码图片宽度
        int height = 300; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        hints.put(EncodeHintType.MARGIN, "1");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
//        File outputFile = new File("d:" + File.separator + "new.jpg");
        File outputFile = new File("d:\\new.jpg");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }
}

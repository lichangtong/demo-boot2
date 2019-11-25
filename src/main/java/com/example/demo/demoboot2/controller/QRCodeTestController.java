package com.example.demo.demoboot2.controller;

import com.example.demo.demoboot2.utils.QRCodeUtil;
import com.example.demo.demoboot2.utils.ShortNetAddressUtil;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: lichangtong
 * @Date: 2019-11-25 11:44
 * @Description:
 */
@Controller
@RequestMapping("/qrcode")
public class QRCodeTestController {
    @RequestMapping(value = "/generateqrcode", method = RequestMethod.GET)
    @ResponseBody
    public void generateQRCode4Product(HttpServletRequest request, HttpServletResponse response) {
        String longUrl;
        try {
            longUrl = "https://www.jianshu.com/u/c0aa31157ba5";
            // 转换成短url
            String shortUrl = ShortNetAddressUtil.generateShortUrl(longUrl);
            // 生成二维码
            BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(shortUrl, response);
            // 将二维码输出到页面中
            MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

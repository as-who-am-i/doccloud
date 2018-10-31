package com.zhiyou100.doccloud.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/*
*@ClassName:PdfUtil
 @Description:TODO
 @Author:
 @Date:2018/10/30 12:15 
 @Version:v1.0
*/
public class PdfUtil {


    public static void main(String[] args) throws IOException {
        // 利用PdfBox生成图像
        PDDocument pdDocument = PDDocument.load(new File("d:\\hadoopclientcode.pdf"));
        PDFRenderer renderer = new PDFRenderer(pdDocument);

// 构造图片
        BufferedImage img_temp = renderer.renderImageWithDPI(0, 105, ImageType.RGB);
// 设置图片格式
        Iterator<ImageWriter> it = ImageIO.getImageWritersBySuffix("jpg");
// 将文件写出
        ImageWriter writer = (ImageWriter) it.next();
        ImageOutputStream imageout = ImageIO.createImageOutputStream(new FileOutputStream("d:\\pdf.jpg"));
        writer.setOutput(imageout);
        writer.write(new IIOImage(img_temp, null, null));
        img_temp.flush();
        imageout.flush();
        imageout.close();
        pdDocument.close();

        //getPage();

    }
}

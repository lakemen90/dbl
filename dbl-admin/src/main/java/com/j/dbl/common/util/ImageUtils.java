package com.j.dbl.common.util;

import com.j.dbl.exception.AppErrorException;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * @author Jiangbin
 * @create 2018-07-04 8:58
 * @desc
 **/
public class ImageUtils {

    /**
     * 将图片压缩到指定大小以内
     *
     * @param srcImgData 源图片数据
     * @param maxSize    目的图片大小
     * @return 压缩后的图片数据
     */
    public static byte[] compressUnderSize(byte[] srcImgData, long maxSize, String suffix) {
        double scale = 0.5;
        byte[] imgData = Arrays.copyOf(srcImgData, srcImgData.length);

        if (imgData.length > maxSize) {
            //System.out.println(new BigDecimal(maxSize).divide(new BigDecimal(imgData.length),4,BigDecimal.ROUND_UP));
            scale = new BigDecimal(maxSize).divide(new BigDecimal(imgData.length),4,BigDecimal.ROUND_UP).doubleValue();
            if(scale <= Double.MIN_VALUE){
                throw new AppErrorException("图片太大...");
            }
            try {
                imgData = compress(imgData, scale, suffix);

            } catch (IOException e) {
                throw new IllegalStateException("压缩图片过程中出错...", e);
            }
//            do {
//                try {
//                    imgData = compress(imgData, scale, suffix);
//
//                } catch (IOException e) {
//                    throw new IllegalStateException("压缩图片过程中出错...", e);
//                }
//
//            } while (imgData.length > maxSize);
        }

        return imgData;
    }

    /**
     * 按照 宽高 比例压缩
     *
     * @param srcImgData 待压缩图片输入流
     * @param scale      压缩刻度
     * @return 压缩后图片数据
     * @throws IOException 压缩图片过程中出错
     */
    public static byte[] compress(byte[] srcImgData, double scale, String suffix) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
        int width = (int) (bi.getWidth() * scale); // 源图宽度
        int height = (int) (bi.getHeight() * scale); // 源图高度

        Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ImageIO.write(tag, suffix, bOut);

        return bOut.toByteArray();
    }

}



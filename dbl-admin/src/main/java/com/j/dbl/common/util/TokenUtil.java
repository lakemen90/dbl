package com.j.dbl.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import io.netty.handler.codec.base64.Base64Encoder;


public class TokenUtil {

	/**
	 * 生成token
	 * @param memberId 会员Id
	 * @return 
	 */
    public static String generateTokeCode(Long memberId){
        String value = System.currentTimeMillis()+new Random().nextInt()+memberId+"";
        System.out.println(value); 
       
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());
            //Base64编码
            Base64Encoder be = new Base64Encoder();
            String token = be.toString();
            System.out.println("token======="+token); 
            if(token.contains("+")){
            	token = token.replace("+", "m");
            }
            System.out.println("tokenReplace======="+token);
            return token;//制定一个编码
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
    	String aa = TokenUtil.generateTokeCode(11L);
    }
}

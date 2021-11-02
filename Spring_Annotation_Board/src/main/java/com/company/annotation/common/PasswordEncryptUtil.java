package com.company.annotation.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncryptUtil {

	public static String encryptSHA256(String plainText){
		String sha256 = "";
			
		try {
			MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
			
			mdSHA256.update(plainText.getBytes("UTF-8"));
			byte[] sha256Hash = mdSHA256.digest();
				
			//16���� ��ȯ
			StringBuffer hexSHA256 = new StringBuffer();
				
			for(byte b : sha256Hash) {
				String hexString = String.format("%02x", b); //16���� 2�ڸ�
				hexSHA256.append(hexString);
			}
			sha256 = hexSHA256.toString();
				
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha256;
	}
}

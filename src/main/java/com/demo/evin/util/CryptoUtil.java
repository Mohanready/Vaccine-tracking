package com.demo.evin.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CryptoUtil {
		
	private static final String SECRET_KEY = "!@#$abichary!@#$";

	private static final String ALGORITHM = "AES";
	private static final String MAC_ALGORITHM = "HmacSHA256";

	public static String encrypt(String Data) {
		try {
			Key key = CryptoUtil.generateKey(encodeKey(SECRET_KEY));
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(Data.getBytes());
			String encryptedValue = Base64.getEncoder().encodeToString(encVal);
			return encryptedValue;	
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
	public static String decrypt(String encryptionText) {
		try {
			Key key = CryptoUtil.generateKey(encodeKey(SECRET_KEY));
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptionText)));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
	private static Key generateKey(String secret) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
		Key key = new SecretKeySpec(decoded, ALGORITHM);
		return key;
	}

	public static String decodeKey(String str) {
		byte[] decoded = Base64.getDecoder().decode(str.getBytes());
		return new String(decoded);
	}

	public static String encodeKey(String str) {
		byte[] encoded = Base64.getEncoder().encode(str.getBytes());
		return new String(encoded);
	}
}

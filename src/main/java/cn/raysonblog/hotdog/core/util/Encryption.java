package cn.raysonblog.hotdog.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 加密类
 * @author rayson
 *
 */
public class Encryption { 
private static char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static String MD5(String input_key) {                     
        return MD5_encryption(input_key);									
    }
	
	 public static String MD5_encryption(String s) {    
		 char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		 String Result;
		 
	     try{
	    	 byte[] btInput = s.getBytes("UTF-8");		
	    	 MessageDigest mdInst = MessageDigest.getInstance("MD5"); 
	         mdInst.update(btInput);				
	         byte[] md = mdInst.digest();			
	         int j = md.length;						
	         char str[] = new char[j * 2];
	         int k = 0;
	         for(int i = 0; i < j; i++) {
	        	 byte byte0 = md[i];
	             str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	             str[k++] = hexDigits[byte0 & 0xf];
	         }
	         Result = new String(str);
	         return Result;
	     }catch(Exception e){
	    	 e.printStackTrace();
	         return null;
	     }
	 }
	 

	public static String SHA_512(String input_str) throws UnsupportedEncodingException{								
		String key = SHA_512_encryption(input_str);   
		return key;
	}
		
	private static String SHA_512_encryption(String Text) throws UnsupportedEncodingException{						
		String Result = null; 
		
		if(Text != null && Text.length()>0){    	
			try{
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-512"); 
				byte[] MiWen = Text.getBytes("utf-8");
				messageDigest.update(MiWen);  									
				byte byteBuffer[] = messageDigest.digest();  						
				StringBuffer strHexString = new StringBuffer();  					
				for (int i = 0; i < byteBuffer.length; i++){  						
					String hex = Integer.toHexString(0xff & byteBuffer[i]);  
					if (hex.length() == 1){  
						strHexString.append('0');  
					}  
					strHexString.append(hex);  
				}  
				Result = strHexString.toString();  
			}  
			catch(NoSuchAlgorithmException e){  
				e.printStackTrace();  
				return null;
			}
		}
		return Result;  
	} 
	
	public static String sha1(String str){
		 if (str == null) {  
	            return null;  
	        }  
	        try {  
	            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");  
	            messageDigest.update(str.getBytes("UTF-8"));  
	            return getFormattedText(messageDigest.digest());  
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
		
	}
	
	/** 
     * Takes the raw bytes from the digest and formats them correct. 
     *  
     * @param bytes 
     *            the raw bytes from the digest. 
     * @return the formatted bytes. 
     */  
    private static String getFormattedText(byte[] bytes) {  
        int len = bytes.length;  
        StringBuilder buf = new StringBuilder(len * 2);  
        // 把密文转换成十六进制的字符串形式  
        for (int j = 0; j < len; j++) {  
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);  
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);  
        }  
        return buf.toString();  
    }  
}
import javax.crypto.Cipher;

import javax.crypto.spec.IvParameterSpec;

import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

import java.util.Base64.Decoder;

import java.io.UnsupportedEncodingException;


 
// Shell Command : echo "brucebrucebruce" | openssl enc -e -aes-128-cbc -base64 -K 1234567890ABCDEF1234567890ABCDEF -iv ABCDEF1234567890ABCDEF1234567890
// Cipher Text : 4LXcu3CoLtHzF6B0XVgJBDU4i77gal4pl3n+sxIulV4=
 

public class AES_DEC {

 

        public static byte[] hexStringToByteArray(String s) {

            int len = s.length();

            byte[] data = new byte[len / 2];

            for (int i = 0; i < len; i += 2) {

                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)

                                     + Character.digit(s.charAt(i+1), 16));

            }

            return data;

        }

       

       

        public static String byteArrayToHexString(byte[] bytes){

              

               StringBuilder sb = new StringBuilder();

              

               for(byte b : bytes){

                      

                       sb.append(String.format("%02X", b&0xff));

               }

              

               return sb.toString();

        }

       
       

        public void run() throws Exception {

                //String key = "eflIpycNQmpJ8dVYuRo1fQ==";

                //String iv = "RkVEQ0JBMDk4NzY1NDMyMQ==";

              

              

                String hex_key ="1234567890ABCDEF1234567890ABCDEF";

                String hex_iv = "ABCDEF1234567890ABCDEF1234567890";

           //     String c = "w4rNkzZw+52qezaIQaD1Qa9HCsHqAVsY4E1sUbYEucI=";

                String c = "4LXcu3CoLtHzF6B0XVgJBDU4i77gal4pl3n+sxIulV4=";  

                

 

              

              

                //bytes로 변환

              //  byte[] byteKey = key.getBytes();

              //  byte[] byteIv = iv.getBytes();

                byte[] byteC = c.getBytes();

                //bytes에서 base64로 디코딩

              //  byte[] decodedByteKey = base64Decode(byteKey);

              //  byte[] decodedByteIv = base64Decode(byteIv);

               

                byte[] decodedByteKey = hexStringToByteArray(hex_key);

                byte[] decodedByteIv = hexStringToByteArray(hex_iv);

                byte[] decodedByteC = base64Decode(byteC);

                //base64로 디코딩된 bytes들을 decode 메소드로 넘겨주고 결과값을 answer로 받음

                String answer = decode(decodedByteC, decodedByteKey, decodedByteIv);

                System.out.println(answer);

            }

            public byte[] base64Decode(byte[] bytes) { //base64로 변환해주는 메소드

                Decoder decoder = Base64.getDecoder();

                byte[] decodedBytes = decoder.decode(bytes);

                return decodedBytes;

            }

           

            public String decode(byte[] decodedByteC ,byte[] decodedByteKey, byte[] decodedByteIv) throws Exception{

                //c를 디코드 하기 위한 메소드

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //cipher 모드 설정

                SecretKeySpec key = new SecretKeySpec(decodedByteKey, "AES"); //cipher에 사용할 key 생성

                IvParameterSpec iv = new IvParameterSpec(decodedByteIv); //cipher에 사용할 iv 생성

                cipher.init(Cipher.DECRYPT_MODE, key, iv); //cipher에 key와 iv, 모드 넣고 초기화

                String decodedString = new String(cipher.doFinal(decodedByteC)); //디코딩할 문장 넣고 return 받기

                return decodedString; //플레인 텍스트

            }

            public static void main(String[] args) throws Exception {

                AES_DEC aes = new AES_DEC();

                aes.run();

            }

}
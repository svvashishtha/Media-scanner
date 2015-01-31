package com.example.saurabh.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;


public class folder_open extends Activity {
    String path;
    File file,files[];
    List<File> file_list;
    ListView listView;
    fileAdapter adapter;
    Context context;
    Button path_Butt;
    boolean doubleBackToExitPressedOnce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_open);
        path_Butt = (Button)findViewById(R.id.temptext);
        Intent intent = getIntent();
        path = intent.getStringExtra("path_default");
        path_Butt.setText(path);
        file = new File(path);
        files = file.listFiles();
        listView = (ListView)findViewById(R.id.second_List);
        context = getApplicationContext();
        adapter = new fileAdapter(context,files);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(files[position].isDirectory())
                {
                    Intent intent = new Intent(folder_open.this,folder_open.class);
                    Log.i("path",files[position].getAbsolutePath());
                    intent.putExtra("path_default",files[position].getAbsolutePath());
                    startActivityForResult(intent,0);
                }
                else
                {
                    Intent intent1 = new Intent();
                    intent1.putExtra("path",files[position].getAbsolutePath());
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            }

        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK){
        Intent intent1 = new Intent();
        String return_path = data.getStringExtra("path");
        intent1.putExtra("path",return_path);
        setResult(RESULT_OK,intent1);
        finish();}
       else
        {
            setResult(RESULT_CANCELED);
            finish();
        }
    }



}

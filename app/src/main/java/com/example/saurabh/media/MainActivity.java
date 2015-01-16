package com.example.saurabh.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends Activity implements MediaScannerConnection.MediaScannerConnectionClient {
    public Button button;
    File file ;//= new File("/storage/sdcard1/music/");
    String path,path_start = "/storage/sdcard1/";
    File fileList[];
   Context context;
    CheckBox internal,external;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        Log.i("in","onCreate");
        button = (Button)findViewById(R.id.butt);
        final EditText editText = (EditText)findViewById(R.id.pathscan);
        internal = (CheckBox)findViewById(R.id.internal);
        external = (CheckBox)findViewById(R.id.external);
        external.setChecked(true);
        internal.setChecked(false);
        external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(external.isChecked() == true)
                {
                    internal.setChecked(false);
                    path_start = "/storage/sdcard1/";
                }

            }
        });
        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internal.isChecked()== true)
                {
                    external.setChecked(false);
                    path_start = "/storage/sdcard0/";

                }
            }
        });
        /*if(file.isDirectory())
        //{
            fileList = file.listFiles();
            for(int i =0 ; i<fileList.length;i++)
            {
                Log.i("File :",fileList[i].getName().toString());
            }
        }
        else
        {
            Log.i("failure","failed");
        }*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   //getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                         //  + Environment.getExternalStorageDirectory())));
                // new MediaScannerConnection(getApplicationContext(),mediaScannerConnectionClient).connect();
                path = path_start + editText.getText().toString() + "/";
                file = new File(path);
                if(file.isDirectory()) {
                    fileList = file.listFiles();
                    Toast.makeText(getApplicationContext(),"Media scanner running...",Toast.LENGTH_SHORT).show();
                    for(int i =0 ; i<fileList.length;i++) {
                        Log.i("File :",fileList[i].getName().toString());
                        new SingleMediaScanner(context, fileList[i]);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),editText.getText().toString() + " is not a valid directory",Toast.LENGTH_LONG).show();
                    Log.i("path",""+path);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMediaScannerConnected() {

    }

    @Override
    public void onScanCompleted(String path, Uri uri) {

    }
}

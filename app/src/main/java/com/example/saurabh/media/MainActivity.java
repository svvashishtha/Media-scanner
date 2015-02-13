package com.example.saurabh.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
public class MainActivity extends Activity  {
    public Button scan_button,browse_button;
    File file ;//= new File("/storage/sdcard1/music/");
    String path,path_scan,path_start = "/storage/sdcard1/";
    File fileList[];
    Context context;
    CheckBox internal,external;
    EditText editText ;
    boolean temp = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        Log.i("in", "onCreate");
        editText  = (EditText)findViewById(R.id.pathscan);
        browse_button = (Button)findViewById(R.id.browse);
        scan_button = (Button)findViewById(R.id.butt);

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
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                file = new File( path_start + editText.getText().toString() + "/");
                if (file.isDirectory()) {
                    fileList = file.listFiles();
                    Toast.makeText(getApplicationContext(), "Media scanner running...", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < fileList.length; i++) {
                        Log.i("File :", fileList[i].getName());
                        new SingleMediaScanner(context, fileList[i]);
                    }
                }
                else if(temp){
                    Log.i("wasted",path_scan);
                    file = new File(path_scan);

                    new SingleMediaScanner(context,file);
                    Toast.makeText(getApplicationContext(), "Media scanner running...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), editText.getText() +" is not a valid directory/file", Toast.LENGTH_SHORT).show();
                }
            }
        });
        browse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,folder_open.class);
                if(external.isChecked())
                {
                    intent.putExtra("path_default","/storage/sdcard1/");
                }
                else if(internal.isChecked())
                {
                    intent.putExtra("path_default","/storage/sdcard0/");
                }
                else
                {
                    intent.putExtra("path_default","/");
                }

                startActivityForResult(intent,0);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 0 && resultCode == RESULT_OK)
        {
            path_scan = data.getStringExtra("path");
            File file2 = new File(path_scan);
            editText.setText(file2.getName());
            temp = true;
        }
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
        // automatically handle clicks on the Home/Up scan_button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

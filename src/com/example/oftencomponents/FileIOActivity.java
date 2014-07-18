package com.example.oftencomponents;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.*;

/**
 * Created by hooxin on 14-7-18.
 */
public class FileIOActivity extends Activity {
    private final String FILE_NAME="crazyit.bin";
    private Button writeButton;
    private Button readButton;
    private EditText displaEditText;
    private EditText writeEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_io);

        readButton=(Button)findViewById(R.id.fileIOReadBtn);
        writeButton= (Button) findViewById(R.id.fileIOWriteBtn) ;
        displaEditText= (EditText) findViewById(R.id.fileIODisplayEdit);
        writeEditText= (EditText) findViewById(R.id.fileIOWriteEdit);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write(writeEditText.getText().toString());
                writeEditText.setText("");
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaEditText.setText(read());
            }
        });
    }

    private String read() {
        FileInputStream fis = null;
        try{
            fis=openFileInput(FILE_NAME);
            byte[] buff=new byte[1024];
            int index=0;
            StringBuilder sb=new StringBuilder("");
            while ((index=fis.read(buff))>0){
                sb.append(new String(buff,0,index));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           if (fis!=null){
               try {
                   fis.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
        return null;
    }

    private void write(String content) {
        FileOutputStream fos = null;
        try{
            fos=openFileOutput(FILE_NAME,MODE_APPEND);
            PrintStream ps=new PrintStream(fos);
            ps.println(content);
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
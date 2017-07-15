package com.opengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glView;
    private GLRenderer glRenderer;


    TextView Count,textView;
    EditText editText ;
    String filename ;
    Button open, change ;
    LinearLayout Layout;

    String buttonText;
    String modelchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.filename) ;
        open =(Button)findViewById(R.id.open) ;
        open.setText("open");
        change =(Button)findViewById(R.id.change) ;
        change.setText("spin");
        buttonText = change.getText().toString();
        textView = (TextView)findViewById(R.id.text) ;
        Count = (TextView)findViewById(R.id.count) ;

        Layout = (LinearLayout)findViewById(R.id.z);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(glView==null) {
                    filename = editText.getText().toString();
                    editText.setFocusable(false);
                    buttonText = change.getText().toString();
                    open.setText("close");
                    textView.setText("目标路径：/sdcard/" + filename);
                    Log.i("TAG","_______________________按下按钮______________________________");
                    glView = new GLSurfaceView(MainActivity.this);
                    glRenderer = new GLRenderer(buttonText ,filename,handlercount);
                    glView.setRenderer(glRenderer);
                    Layout.addView(glView);
                }
                else  {
                    finish();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonText = change.getText().toString();
                if(glView==null && buttonText == "spin")
                {
                    change.setText("static");
                }
                else if(glView==null && buttonText == "static")
                    change.setText("spin");
            }});

    }


    public Handler handlercount = new Handler()
    {
        public void handleMessage(Message msg)
        {
            try {
                String UI = msg.getData().getString("UI");
                Count.setText(UI);
            }
            catch (Exception E){}
        }

    };

    @Override
    //当前Activity由被覆盖状态回到前台或解锁屏：系统会调用onResume方法，再次进入运行状态
    //继续绘图
    protected void onResume() {
        super.onResume();
        if (glView != null) {
            glView.onResume();
        }
    }

    @Override
    //当前Activity被其他Activity覆盖其上或被锁屏：系统会调用onPause方法，暂停当前Activity的执行。
    //暂停绘图
    protected void onPause() {
        super.onPause();
        if (glView != null) {
            glView.onPause();
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }


}

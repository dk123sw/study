package afloat.com.fileceshi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mobstat.StatService;

import afloat.com.fileceshi.SwipeRefreshLayout.RefreshLayotActivity;
import afloat.com.fileceshi.share.ShareActivity;
import afloat.com.fileceshi.umshare.UmengActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatService.setDebugOn(true);
        StatService.start(this);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this , UmengActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(MainActivity.this, ActivityPageTest.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(MainActivity.this ,ShareActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(MainActivity.this , RefreshLayotActivity.class);
                startActivity(intent3);
                break;
            case R.id.button5:

                break;
        }
    }
}

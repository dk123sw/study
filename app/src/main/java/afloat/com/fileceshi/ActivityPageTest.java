package afloat.com.fileceshi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mobstat.StatService;

/**
 * Created by hst028 on 2017/6/28.
 *
 * 测试打开页面
 */

public class ActivityPageTest extends Activity{
    private Button onEventButton;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_test);
        mButton = (Button) findViewById(R.id.page_exit);
        onEventButton = (Button) findViewById(R.id.button1);
        initView();
    }

    private void initView() {
        onEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityPageTest.this, "测试", Toast.LENGTH_LONG).show();
                StatService.onEvent(ActivityPageTest.this, "button", "ceshi");
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPageTest.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
/**
 * 用onResume,onPause会显示类的名字，用OnPageStart可以自定义页面
 */
    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(ActivityPageTest.this);
//       StatService.onPageStart(ActivityPageTest.this , "自定义的页面名称");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(ActivityPageTest.this);
//        StatService.onPageEnd(ActivityPageTest.this , "自定义的页面名称");
    }
}

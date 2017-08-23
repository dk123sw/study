package afloat.com.fileceshi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mobstat.StatService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import afloat.com.fileceshi.SwipeRefreshLayout.RefreshLayotActivity;
import afloat.com.fileceshi.function.MessageEvent;
import afloat.com.fileceshi.function.functionActivity;
import afloat.com.fileceshi.huanxin.ECMainActivity;
import afloat.com.fileceshi.share.ShareActivity;
import afloat.com.fileceshi.umshare.UmengActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatService.setDebugOn(true);
        StatService.start(this);
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5 , R.id.button6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this, UmengActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(MainActivity.this, ActivityPageTest.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(MainActivity.this, RefreshLayotActivity.class);
                startActivity(intent3);
                break;
            case R.id.button5:
                Intent intent4 = new Intent(MainActivity.this, ECMainActivity.class);
                startActivity(intent4);
                break;
            case R.id.button6:
                Intent intent5 = new Intent(MainActivity.this , functionActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusS(MessageEvent messageEvent){
        mButton6.setText(messageEvent.getMessage());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mButton6.setText("小功能小问题测试");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

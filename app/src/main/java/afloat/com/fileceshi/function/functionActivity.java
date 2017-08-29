package afloat.com.fileceshi.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import afloat.com.fileceshi.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class functionActivity extends Activity {


    @BindView(R.id.status_test)
    Button mStatusTest;
    @BindView(R.id.tool_test)
    Button mToolTest;
    @BindView(R.id.guide_test)
    Button mGuide;
    @BindView(R.id.eventbus_test)
    Button mEventbusTest;
    @BindView(R.id.checkrefresh_test)
    Button mCheckrefreshTest;
    @BindView(R.id.richEditText_test)
    Button mRichEditTextTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.status_test, R.id.tool_test, R.id.guide_test, R.id.eventbus_test, R.id.checkrefresh_test,R.id.richEditText_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.status_test:
                Intent intent = new Intent(this, StatusActivity.class);
                startActivity(intent);
                break;
            case R.id.tool_test:
                Intent intent1 = new Intent();
                intent1.setClass(this, ToolTestActivity.class);
                startActivity(intent1);
                break;
            case R.id.guide_test:
                Intent intent2 = new Intent(this, GuideActivity.class);
                startActivity(intent2);
                break;
            case R.id.eventbus_test:
                Intent intent3 = new Intent(this, EventBusActivity.class);
                startActivity(intent3);
                break;
            case R.id.checkrefresh_test:
                Intent intent4 = new Intent(this, CheckRefeshActivity.class);
                startActivity(intent4);
                break;
            case R.id.richEditText_test:
                Intent intent5 = new Intent(this, RichEditActivity.class);
                startActivity(intent5);
                break;
        }
    }
}

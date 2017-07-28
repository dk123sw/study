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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.status_test, R.id.tool_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.status_test:
                Intent intent = new Intent(this, StatusActivity.class);
                startActivity(intent);
                break;
            case R.id.tool_test:
                Intent intent1 = new Intent();
                intent1.setClass(this , ToolTestActivity.class);
                startActivity(intent1);
                break;
        }
    }
}

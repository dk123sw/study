package afloat.com.fileceshi.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import afloat.com.fileceshi.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class functionActivity extends Activity {

    @BindView(R.id.button_use1)
    Button mButtonUse1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_use1)
    public void onViewClicked() {
        Intent intent = new Intent(functionActivity.this , StatusActivity.class);
        startActivity(intent);
    }
}

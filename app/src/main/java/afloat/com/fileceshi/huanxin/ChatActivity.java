package afloat.com.fileceshi.huanxin;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.umeng.analytics.MobclickAgent;

import afloat.com.fileceshi.R;

public class ChatActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        initView();
        myOnClick();
    }
    public void initView() {
//      连接easeUi直接封装好的聊天界面
        EaseChatFragment easeChatFragment=new EaseChatFragment();
//      将参数传到聊天界面
        easeChatFragment.setArguments(getIntent().getExtras());
//      加载easeUi封装的聊天界面的fragment
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container,easeChatFragment).commit();

    }
    public void myOnClick() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}

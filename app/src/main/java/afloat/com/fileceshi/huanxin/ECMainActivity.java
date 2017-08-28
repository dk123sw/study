package afloat.com.fileceshi.huanxin;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;

import afloat.com.fileceshi.R;

public class ECMainActivity extends AppCompatActivity {

    // 发起聊天 username 输入框
    private EditText mChatIdEdit;
    // 发起聊天
    private Button mStartChatBtn;
    // 退出登录
    private Button mSignOutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断sdk是否登录成功过，并没有退出和被踢，否则跳转到登陆界面
        if (!EMClient.getInstance().isLoggedInBefore()) {
            Intent intent = new Intent(ECMainActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.haunxin_activity);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

        mChatIdEdit = (EditText) findViewById(R.id.ec_edit_chat_id);

        mStartChatBtn = (Button) findViewById(R.id.ec_btn_start_chat);
        mStartChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取我们发起聊天的者的username
                String chatId = mChatIdEdit.getText().toString().toString();
                if (!TextUtils.isEmpty(chatId)) {
                    // 获取当前登录用户的 username
                    String currUsername = EMClient.getInstance().getCurrentUser();
                    if (chatId.equals(currUsername)) {
                        Toast.makeText(ECMainActivity.this, "不能和自己聊天", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 跳转到聊天界面，开始聊天
                    Intent intent = new Intent(ECMainActivity.this, ChatActivity.class);
                    // 传递注册的名字
                    intent.putExtra(EaseConstant.EXTRA_USER_ID,chatId);
                    // EMMessage.ChatType.Chat单聊，还有群聊和聊天室
//                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                    startActivity(intent);
                } else {
                    Toast.makeText(ECMainActivity.this, "Username 不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });

        mSignOutBtn = (Button) findViewById(R.id.ec_btn_sign_out);
        mSignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    /**
     * 退出登录
     */
    private void signOut() {
        // 调用sdk的退出登录方法，第一个参数表示是否解绑推送的token，没有使用推送或者被踢都要传false
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.i("lzan13", "logout success");
                // 调用退出成功，结束app
                finish();
            }

            @Override
            public void onError(int i, String s) {
                Log.i("lzan13", "logout error " + i + " - " + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

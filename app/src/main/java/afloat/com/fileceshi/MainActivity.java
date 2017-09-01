package afloat.com.fileceshi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.mobstat.StatService;
import com.huawei.android.pushagent.PushService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import afloat.com.fileceshi.SwipeRefreshLayout.RefreshLayotActivity;
import afloat.com.fileceshi.function.EditTextActivity;
import afloat.com.fileceshi.function.MessageEvent;
import afloat.com.fileceshi.function.functionActivity;
import afloat.com.fileceshi.huanxin.ECMainActivity;
import afloat.com.fileceshi.share.ShareActivity;
import afloat.com.fileceshi.statistics.ActivityPageTest;
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
    @BindView(R.id.button7)
    Button mButton7;
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_MIUI = "sys_miui";
    public static final String SYS_FLYME = "sys_flyme";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
    private static final String TAG = "MainActivity";
    private boolean sign = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatService.setDebugOn(true);
        StatService.start(this);
        EventBus.getDefault().register(this);
        getSystem();//获取当前手机操作系统
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7})
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
                Intent intent5 = new Intent(MainActivity.this, functionActivity.class);
                startActivity(intent5);
                break;
            case R.id.button7:
                openStart(this);
        }
    }

    /**
     * 获取当前手机操作系统
     */
    public static String getSystem() {
        String SYS = "SYS_OTHER";
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                SYS = SYS_MIUI;//小米
                Log.d(TAG, "小米");
            } else if (prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    || prop.getProperty(KEY_EMUI_VERSION, null) != null
                    || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                SYS = SYS_EMUI;//华为
                Log.d(TAG, "华为 ");
            } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                SYS = SYS_FLYME;//魅族
                Log.d(TAG, "魅族");
            } else {
                Log.d(TAG, "其他11");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "其他22");
            return SYS;
        }
        return SYS;
    }

    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }



    /**
     * 打开"自启动管理"界面
     */
    public void openStart(Context context) {
//        if(Build.VERSION.SDK_INT < 14){
//            Toast.makeText(this,"版本太低",Toast.LENGTH_SHORT).show();
//            return;
//        }
        sign = true;
        String system = getSystem();
        Intent intent = new Intent();
        if (system.equals(SYS_EMUI)) {//华为
            ComponentName componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
            intent.setComponent(componentName);
        } else if (system.equals(SYS_MIUI)) {//小米
            ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            intent.setComponent(componentName);
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {//抛出异常就直接打开设置页面
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }

        
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusS(MessageEvent messageEvent) {
        mButton6.setText(messageEvent.getMessage());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mButton6.setText(getResources().getString(R.string.button6));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//      防止服务被杀掉
        Intent intent = new Intent(this , PushService.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
        if (sign){
            Intent i = new Intent(this , EditTextActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
}

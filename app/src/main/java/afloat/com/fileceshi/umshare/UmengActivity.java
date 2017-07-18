package afloat.com.fileceshi.umshare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import afloat.com.fileceshi.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UmengActivity extends Activity {


    @BindView(R.id.umeng_share_button)
    Button mUmengShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeng);
        ButterKnife.bind(this);
    }

    private void initUmeng() {
        new ShareAction(UmengActivity.this).withText("ceshi")
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(mUMShareListener)
                .open();
    }


    /**
     * 接口回调
     */
    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(UmengActivity.this, "分享开始", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(UmengActivity.this, "onResult", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(UmengActivity.this, "onError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(UmengActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
        }
    };

    @OnClick(R.id.umeng_share_button)
    public void onViewClicked() {
        initUmeng();
    }
}

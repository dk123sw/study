package afloat.com.fileceshi.share;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;

import java.util.List;

import afloat.com.fileceshi.Constant;
import afloat.com.fileceshi.R;

public class ShareActivity extends Activity implements View.OnClickListener,PopupWindow.OnDismissListener{

    private ImageView mImageView;
    private SharePopup sharePopup;
    public Context context;
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;
    /**
     * 新浪微博分享
     */
    public static final int SHARE_CLIENT = 1;
    public static final int SHARE_ALL_IN_ONE = 2;
    private WbShareHandler shareHandler;
    private int mShareType = SHARE_CLIENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        context = getApplicationContext();
        WbSdk.install(this,new AuthInfo(context, Constant.APP_KEY, Constant.REDIRECT_URL,
                Constant.SCOPE));
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.img_share);
        mImageView.setOnClickListener(this);

        mSsoHandler = new SsoHandler(ShareActivity.this);
        shareHandler = new WbShareHandler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_share:
                sharePopup = new SharePopup(ShareActivity.this,itemsOnClick);
                // 设置点击其他位置mTestPopwindow2消失
                sharePopup.setOnDismissListener(ShareActivity.this);
                sharePopup.showAtLocation(v,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }

    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            sharePopup.dismiss();
            sharePopup.backgroundAlpha(ShareActivity.this, 1f);
            switch (v.getId()) {
                case R.id.wx_share:
                    if (!isWebchatAvaliable()){
                        Toast.makeText(ShareActivity.this , "请先安装微信" ,Toast.LENGTH_SHORT).show();
                        break;
                    }
                    WechatShareManager.ShareContentText shareContentText = (WechatShareManager.ShareContentText)
                            WechatShareManager.getInstance(context).getShareContentText("测试");
                   WechatShareManager.getInstance(context).shareByWebchat(shareContentText , WechatShareManager.WECHAT_SHARE_TYPE_FRENDS);
                    Toast.makeText(ShareActivity.this , "微信" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.xl_share:
                    if(isWxInstall(context)){
                        mAccessToken = AccessTokenKeeper.readAccessToken(context);
                        if (mAccessToken.isSessionValid()){
                            sendMultiMessage();
                        }else{
                            mSsoHandler.authorizeClientSso(new SelfWbAuthListener());
                        }
                    }else{
                        Toast.makeText(ShareActivity.this , "未安装新浪微博客户端" ,Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDismiss() {

    }

    public boolean isWebchatAvaliable() {
        //检测手机上是否安装了微信
        try {
            getPackageManager().getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查用户是否安装新浪微博
     */
    public static boolean isWxInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 微博授权
     */
    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            ShareActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(context, mAccessToken);
                        sendMultiMessage();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(ShareActivity.this , "取消授权" ,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(ShareActivity.this , "授权失败" ,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMultiMessage() {


        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (true) {
            weiboMessage.textObject = getTextObj();
        }
        if (true) {
            weiboMessage.imageObject = getImageObj();
        }
        weiboMessage.mediaObject = getWebpageObj();
        shareHandler.shareMessage(weiboMessage, mShareType == SHARE_CLIENT);
    }

    /**
     * 创建文本消息对象。
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = "test";
        textObject.title = "xxxx";
        textObject.actionUrl = "http://www.baidu.com";
        return textObject;
    }

    /**
     * 创建图片消息对象。
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }
    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title ="测试title";
        mediaObject.description = "测试描述";
        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wx);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml";
        mediaObject.defaultText = "Webpage 默认文案";
        return mediaObject;
    }
}

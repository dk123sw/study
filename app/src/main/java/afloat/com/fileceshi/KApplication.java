/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package afloat.com.fileceshi;

import android.app.Application;
import android.content.Context;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

import afloat.com.fileceshi.share.WechatShareManager;

/**
 * Application类，应用级别的操作都放这里
 *
 * @version 1.0 创建时间：15/6/25
 */
public class KApplication extends Application {

    private static KApplication instance;
    /**
     * 全局的上下文
     */
    private static Context mContext;
    /**
     * 单例模式
     */
    public static KApplication getInstance() {
        return instance;
    }

    public static void setInstance(KApplication instance) {
        KApplication.instance = instance;
    }
    private static WechatShareManager mShareManager;
//    private UserInfo userInfo;
    @Override
    public void onCreate() {
        super.onCreate();
//        ImageLoader.init(getApplicationContext());
        setInstance(this);
//        appAction = new AppActionImpl(this);
        mContext = getApplicationContext();

        //微信
        setmShareManager(WechatShareManager.getInstance(mContext));
        WbSdk.install(this,new AuthInfo(mContext, Constant.APP_KEY, Constant.REDIRECT_URL,
                Constant.SCOPE));}



    public static WechatShareManager getmShareManager() {
        return mShareManager;
    }

    public static void setmShareManager(WechatShareManager mShareManager) {
        KApplication.mShareManager = mShareManager;
    }

    /**
     * 获取context
     */
    public static Context getContext() {
        return mContext;
    }



}

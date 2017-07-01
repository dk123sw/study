package afloat.com.fileceshi.share;

/**
 * Created by hst on 2017/4/24.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import afloat.com.fileceshi.R;


/**
 * 自定义popupWindow
 */

public class SharePopup extends PopupWindow {
    private View mView;
    private Context context;
    public SharePopup(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        initView(context, itemsOnClick);
        this.context = context;
    }

    private void initView(final Activity context, View.OnClickListener itemsOnClick) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.popup_share, null);
        LinearLayout wxshare = (LinearLayout) mView.findViewById(R.id.wx_share);
        LinearLayout xlshare = (LinearLayout) mView.findViewById(R.id.xl_share);
//        TextView black = (TextView) mView.findViewById(R.id.black);

        //设置按钮监听
        wxshare.setOnClickListener(itemsOnClick);
        xlshare.setOnClickListener(itemsOnClick);
//        black.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        // 设置点击窗口外边窗口消失
        this.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        this.setFocusable(true);

        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context, 0.5f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(context, 1f);
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        // 设置屏幕变回原来的亮度
        WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.alpha = 1.0f;
        ((Activity) context).getWindow().setAttributes(params);
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

}
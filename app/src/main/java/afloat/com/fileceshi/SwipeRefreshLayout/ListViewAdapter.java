package afloat.com.fileceshi.SwipeRefreshLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import afloat.com.fileceshi.R;

/**
 * Created by hst028 on 2017/7/1.
 */

public class ListViewAdapter extends BaseAdapter {

    /** 环境信息 **/
    private Context context;
    /** 用户中心信息集合 **/
    private ArrayList<SoftwareClassificationInfo> list;

    /** 构�?方法 **/
    public ListViewAdapter(Context context, ArrayList<SoftwareClassificationInfo> list) {
        this.context = context;
        this.list = list;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup arg2) {
        _View view = null;
        if (convertView == null) {
            view = new _View();
            convertView = LayoutInflater.from(context).inflate(R.layout.user_center_list_item, null);
            view.list_text = (TextView) convertView.findViewById(R.id.user_center_item_txt);
            view.list_img = (ImageView) convertView.findViewById(R.id.user_center_item_img);
            convertView.setTag(view);
        } else {
            view = (_View) convertView.getTag();
        }
        view.list_text.setText(list.get(position).getCatname());
        view.list_img.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.tool_box_fragment_settings_icon));
        return convertView;
    }

    class _View {
        TextView list_text;
        ImageView list_img;
    }

}

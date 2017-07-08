package afloat.com.fileceshi.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.ArrayList;

import afloat.com.fileceshi.R;

    public class RefreshLayotActivity extends Activity implements
            SwipeRefreshLayout.OnRefreshListener {
        private SwipeRefreshLayout swipeLayout;
        private ListView listView;
        private ListViewAdapter adapter;
        private ArrayList<SoftwareClassificationInfo> list;
        private boolean isRefresh = false;//是否刷新中

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.swipe_refresh_layout);
            swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
            swipeLayout.setOnRefreshListener(this);
            //加载颜色是循环播放的，只要没有完成刷新就会一直循环，color1>color2>color3>color4
//            swipeLayout.setColorScheme(getResources().getColor(android.R.color.white),
//                    getResources().getColor(android.R.color.holo_green_light),
//                    getResources().getColor(android.R.color.holo_orange_light),
//                    getResources().getColor(android.R.color.holo_orange_light));
            swipeLayout.setColorSchemeResources(R.color.colorAccent);

            list = new ArrayList<SoftwareClassificationInfo>();
            list.add(new SoftwareClassificationInfo(1, "asdas"));
            listView = (ListView) findViewById(R.id.list);
            adapter = new ListViewAdapter(this, list);
            listView.setAdapter(adapter);
        }

        public void onRefresh() {
            if (!isRefresh) {
                isRefresh = true;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        swipeLayout.setRefreshing(false);
                        list.add(new SoftwareClassificationInfo(2, "ass"));
//                        adapter.notifyDataSetChanged();
                        isRefresh = false;
                    }
                }, 3000);
            }
        }
    }


package com.yellon.treelistview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.yellon.treelistview.Adapter.SimpleTreeAdapter;
import com.yellon.treelistview.Adapter.TreeListViewAdapter;
import com.yellon.treelistview.R;
import com.yellon.treelistview.bean.Bean;
import com.yellon.treelistview.bean.TreeBean.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-22 18:57
 * @package com.yellon.treelistview.Activity
 * @description TreeListActivity  树状ListView
 */
public class TreeListActivity extends Activity {

    private ListView tree;
    private TreeListViewAdapter mAdapter;

    private List<Bean> mDatas = new ArrayList<>();
    private List<Bean> mDatas2 = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_listview);
        initData();
        initView();
    }

    private void initData() {
        mDatas.add(new Bean(1, 0, "根目录1"));
        mDatas.add(new Bean(2, 0, "根目录2"));
        mDatas.add(new Bean(3, 0, "根目录3"));
        mDatas.add(new Bean(4, 0, "根目录4"));
        mDatas.add(new Bean(5, 1, "子目录1-1"));
        mDatas.add(new Bean(6, 1, "子目录1-2"));

        mDatas.add(new Bean(7, 5, "子目录1-1-1"));
        mDatas.add(new Bean(8, 2, "子目录2-1"));

        mDatas.add(new Bean(9, 4, "子目录4-1"));
        mDatas.add(new Bean(10, 4, "子目录4-2"));

        mDatas.add(new Bean(11, 10, "子目录4-2-1"));
        mDatas.add(new Bean(12, 10, "子目录4-2-3"));
        mDatas.add(new Bean(13, 10, "子目录4-2-2"));
        mDatas.add(new Bean(14, 9, "子目录4-1-1"));
        mDatas.add(new Bean(15, 9, "子目录4-1-2"));
        mDatas.add(new Bean(16, 9, "子目录4-1-3"));

        mDatas2.add(new Bean(1, 0, "文件管理系统"));
        mDatas2.add(new Bean(2, 1, "游戏"));
        mDatas2.add(new Bean(3, 1, "文档"));
        mDatas2.add(new Bean(4, 1, "程序"));
        mDatas2.add(new Bean(5, 2, "war3"));
        mDatas2.add(new Bean(6, 2, "刀塔传奇"));

        mDatas2.add(new Bean(7, 4, "面向对象"));
        mDatas2.add(new Bean(8, 4, "非面向对象"));

        mDatas2.add(new Bean(9, 7, "C++"));
        mDatas2.add(new Bean(10, 7, "JAVA"));
        mDatas2.add(new Bean(11, 7, "Javascript"));
        mDatas2.add(new Bean(12, 8, "C"));
    }

    private void initView() {
        tree = (ListView) findViewById(R.id.tree_listView);
        try {
            mAdapter = new SimpleTreeAdapter<Bean>(tree, this, mDatas, 10);
            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()) {
                        Toast.makeText(getApplicationContext(),node.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        tree.setAdapter(mAdapter);
    }

}
package com.yellon.treelistview.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yellon.treelistview.bean.TreeBean.Node;
import com.yellon.treelistview.bean.TreeBean.TreeHelper;

import java.util.List;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-23 09:07
 * @package com.yellon.treelistview.Adapter
 * @description TreeListViewAdapter  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public abstract class TreeListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;

    /**
     * 存储所有可见的Node
     */
    protected List<Node> mNodes;
    protected LayoutInflater mInflater;

    /**
     * 存储所有的Node
     */
    protected List<Node> mAllNodes;

    /**
     * 点击事件的回调接口
     */
    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListene){
        this.onTreeNodeClickListener = onTreeNodeClickListene;
    }

    /**
     * @param mTree
     */
    public TreeListViewAdapter(ListView mTree, Context context, List<T> datas,
                               int defaultExpandLevel) throws IllegalAccessException {
        mContext = context;
        /**
         * 将所有数据转换为节点，并且对所有Node节点进行排序
         */
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        /**
         * 过滤出所有节点中可见的Node
         */
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);

        /**
         * 设置节点点击时，可以展开和关闭，并且将ItemClick事件继续往外公布
         */
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expendOrCollapse(position);
                if(onTreeNodeClickListener != null){
                    onTreeNodeClickListener.onClick(mNodes.get(position),position);
                }
            }
        });
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     *
     * @param position
     */
    public void expendOrCollapse(int position) {
        Node node = mNodes.get(position);
        if (null != node) { //排除传入参数错误异常
            if (!node.isLeaf()) {
                node.setExpand(!node.isExpand());
                //重新过滤可见节点
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();//刷新视图
            }
        }
    }

    @Override
    public int getCount() {
        return mNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        //设置内边距
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
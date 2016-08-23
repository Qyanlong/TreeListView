package com.yellon.treelistview.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yellon.treelistview.R;
import com.yellon.treelistview.bean.TreeBean.Node;

import java.util.List;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-23 11:46
 * @package com.yellon.treelistview.Adapter
 */
public class SimpleTreeAdapter<T> extends TreeListViewAdapter {

    public SimpleTreeAdapter(ListView mTree, Context context, List datas, int defaultExpandLevel) throws IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.treelist_item, parent, false);
            holder.icon = (ImageView) convertView.findViewById(R.id.id_treenode_icon);
            holder.label = (TextView) convertView.findViewById(R.id.id_treenode_label);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(node.getIcon() == -1){
            holder.icon.setVisibility(View.INVISIBLE);
        }else{
            holder.icon.setVisibility(View.VISIBLE);
            holder.icon.setImageResource(node.getIcon());
        }
        holder.label.setText(node.getName());
        return convertView;
    }

    private final class ViewHolder {
        ImageView icon;
        TextView label;
    }
}
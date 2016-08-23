package com.yellon.treelistview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yellon.treelistview.R;

public class MainActivity extends Activity implements View.OnClickListener{

    public Button treeList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        treeList = (Button) findViewById(R.id.bt_tree_listView);
    }


    private void setListener() {
        treeList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.bt_tree_listView:
                intent.setClass(MainActivity.this,TreeListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

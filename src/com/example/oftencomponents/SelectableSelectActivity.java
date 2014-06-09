package com.example.oftencomponents;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by hooxin on 14-6-9.
 */
public class SelectableSelectActivity extends ExpandableListActivity {
    private String[] provinces = new String[]{
            "广东", "广西", "湖南", "重庆"
    };
    private String[][] citys = new String[][]{
            {"广州", "深圳", "珠海", "中山"},
            {"桂林", "柳州", "南宁", "北海"},
            {"长沙", "岳阳", "衡阳", "株洲"},
            {"重庆"}
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return provinces.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return citys[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return provinces[groupPosition];
            }

            @Override
            public Object getChild(int groupPostion, int childPostion) {
                return citys[groupPostion][childPostion];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpand, View view, ViewGroup parent) {
                LinearLayout ll=new LinearLayout(SelectableSelectActivity.this);
                ll.setOrientation(0);
                ImageView logo=new ImageView(SelectableSelectActivity.this);
                ll.addView(logo);
                TextView textView=getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
                TextView textView=getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

            private TextView getTextView(){
                AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView textView=new TextView(SelectableSelectActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;
            }
        };

        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                Intent intent=getIntent();
                Bundle data = new Bundle();
                data.putString("city",citys[groupPosition][childPosition]);
                intent.putExtras(data);
                SelectableSelectActivity.this.setResult(0,intent);
                SelectableSelectActivity.this.finish();
                return false;
            }
        });
    }
}
package com.ma.pingan.comprehensive.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.MyItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PingAn
 * on 2017/7/10 0010
 */

public class VLayoutAdapter extends DelegateAdapter.Adapter<VLayoutAdapter.MainViewHolder> {

    private ArrayList<HashMap<String,Object>> listItem;
    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count=0;

    private MyItemClickListener myItemClickListener;
    // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器
    public void setOnItemClickListener(MyItemClickListener listener) {
        myItemClickListener = listener;
    }
    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public VLayoutAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, Object>> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public VLayoutAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        holder.text.setText((String) listItem.get(position).get("itemTitle"));
        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
    }

    @Override
    public int getItemCount() {
        return count;
    }



    protected class MainViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        public ImageView image;

        public MainViewHolder(View root) {
            super(root);
            text= (TextView) root.findViewById(R.id.Item);
            image = (ImageView) root.findViewById(R.id.Image);

            root.setOnClickListener(v -> {
                if (myItemClickListener !=null){
                    myItemClickListener.onItemClick(v, getPosition());
                }
            });
        }

        public TextView getText(){
            return text;
        }

    }
}

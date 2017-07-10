package com.ma.pingan.comprehensive.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.base.MyItemClickListener;
import com.ma.pingan.comprehensive.bilientity.RegionTypesInfo;
import com.ma.pingan.comprehensive.ui.adapter.VLayoutAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FanjuDetailActivity extends AppCompatActivity implements MyItemClickListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<HashMap<String, Object>> listItem;

    VLayoutAdapter Adapter_linearLayout, Adapter_GridLayout, Adapter_SingleLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanju_detail);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        VirtualLayoutManager layoutManger = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(layoutManger);

        /**
         * 步骤2：设置组件复用回收池
         * */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerview.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 3);

        /**
         * 步骤3:设置需要存放的数据
         * */
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.mipmap.ic_launcher);
            listItem.add(map);

        }
        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(3);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(100);
        // 设置布局底部与下个布局的间隔


        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        Adapter_linearLayout = new VLayoutAdapter(this, linearLayoutHelper, 3, listItem) {
            // 参数2:绑定绑定对应的LayoutHelper
            // 参数3:传入该布局需要显示的数据个数
            // 参数4:传入需要绑定的数据

            // 通过重写onBindViewHolder()设置更丰富的布局效果
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                // 为了展示效果,将布局的第一个数据设置为linearLayout
                if (position == 0) {
                    holder.getText().setText("Linear");
                    // holder.text.setText("Linear");
                }

                //为了展示效果,将布局里不同位置的Item进行背景颜色设置
                if (position < 2) {
                    holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
                } else if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(0xaa22ff22);
                } else {
                    holder.itemView.setBackgroundColor(0xccff22ff);
                }

            }
        };

        Adapter_linearLayout.setOnItemClickListener(this);


        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(10);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 0, 20, 0);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比


        // gridLayoutHelper特有属性
        gridLayoutHelper.setWeights(new float[]{ 46.665f});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(true);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(2);// 设置每行多少个网格
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
//        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//
//
//                if (position > 7) {
//                    return 2;
//                    // 第7个位置后,每个Item占3个网格
//                } else {
//                    return 2;
//                    // 第7个位置前,每个Item占2个网格
//                }
//            }
//        });

        Adapter_GridLayout = new VLayoutAdapter(this, gridLayoutHelper, 10, listItem) {
            // 设置需要展示的数据总数,此处设置是8,即展示总数是8个,然后每行是4个(上面设置的)
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                // 为了展示效果,将布局里不同位置的Item进行背景颜色设置
                if (position < 2) {
                    holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
                } else if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(0xaa22ff22);
                } else {
                    holder.itemView.setBackgroundColor(0xccff22ff);
                }


                if (position == 0) {
                    holder.getText().setText("Grid");
                }
            }
        };

        Adapter_GridLayout.setOnItemClickListener(this);
        // 设置每个Item的点击事件

        /**
         设置固定布局
         */

        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setPadding(20, 20, 20, 20);

        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比


        Adapter_SingleLayout = new VLayoutAdapter(this, singleLayoutHelper, 1, listItem) {
            // 设置需要展示的数据总数,此处设置是1
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Single
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.getText().setText("Single");
                }
            }
        };

        Adapter_SingleLayout.setOnItemClickListener(this);
        // 设置每个Item的点击事件

        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();
        adapters.add(Adapter_linearLayout);
        adapters.add(Adapter_GridLayout);
        adapters.add(Adapter_SingleLayout);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManger);
        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);
        recyclerview.setAdapter(delegateAdapter);
    }


    public static void launch(FragmentActivity activity, RegionTypesInfo.DataBean dataBean) {
        Intent mIntent = new Intent(activity, FanjuDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_PARTITION, dataBean);
        mIntent.putExtras(bundle);
        activity.startActivity(mIntent);

    }


    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第" + postion + "行");
        Toast.makeText(this, (String) listItem.get(postion).get("ItemTitle"), Toast.LENGTH_SHORT).show();

    }
}

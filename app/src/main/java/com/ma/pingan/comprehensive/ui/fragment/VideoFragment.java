package com.ma.pingan.comprehensive.ui.fragment;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseFragment;
import com.ma.pingan.comprehensive.bilientity.IconInfo;
import com.ma.pingan.comprehensive.bilientity.RegionTypesInfo;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.ui.activity.RegionTypeDetailsActivity;
import com.ma.pingan.comprehensive.ui.adapter.HomeRegionItemAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private HomeRegionItemAdapter adapter;
    private List<RegionTypesInfo.DataBean> regionTypes = new ArrayList<>();

    private List<IconInfo> list = new ArrayList<>();

    String[] itemNames = new String[]{
            "直播", "番剧", "动画",
            "音乐", "舞蹈", "游戏",
            "科技", "生活", "鬼畜",
            "时尚", "广告", "娱乐",
            "电影", "电视剧", "游戏中心",
    };

    int[] itemIcons = new int[]{
            R.drawable.ic_category_live, R.drawable.ic_category_t13,
            R.drawable.ic_category_t1, R.drawable.ic_category_t3,
            R.drawable.ic_category_t129, R.drawable.ic_category_t4,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t119, R.drawable.ic_category_t155,
            R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11,
            R.drawable.ic_category_game_center
    };


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {



        for (int i = 0; i < 15; i++) {
            IconInfo info = new IconInfo();
            info.setName(itemNames[i]);
            info.setIcon(itemIcons[i]);
            list.add(info);
        }


        Observable.just(readAssetsJson())
                .map(s -> new Gson().fromJson(s, RegionTypesInfo.class))
                .map(RegionTypesInfo::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBeen -> {
                    regionTypes.addAll(dataBeen);
                }, throwable -> {

                });

    }

    @Override
    public void configViews() {
        adapter = new HomeRegionItemAdapter(R.layout.item_home_region, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    /**
     * 读取assets下的json数据
     */
    private String readAssetsJson() {

        AssetManager assetManager = getActivity().getAssets();
        try {
            InputStream is = assetManager.open("region.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                //直播
               // startActivity(new Intent(getActivity(), LiveAppIndexActivity.class));
                break;

            case 1:
                //番剧
                RegionTypesInfo.DataBean mBangumi = regionTypes.get(1);
              //  RegionTypeDetailsActivity.launch(getActivity(), mBangumi);
                break;

            case 2:
                //动画
                RegionTypesInfo.DataBean mAnimation = regionTypes.get(2);
               // RegionTypeDetailsActivity.launch(getActivity(), mAnimation);
                break;

            case 3:
                //音乐
                RegionTypesInfo.DataBean mMuise = regionTypes.get(3);
               // RegionTypeDetailsActivity.launch(getActivity(), mMuise);
                break;

            case 4:
                //舞蹈
                RegionTypesInfo.DataBean mDence = regionTypes.get(4);
              //  RegionTypeDetailsActivity.launch(getActivity(), mDence);
                break;

            case 5:
                //游戏
                RegionTypesInfo.DataBean mGame = regionTypes.get(5);
               // RegionTypeDetailsActivity.launch(getActivity(), mGame);
                break;

            case 6:
                //科技
                RegionTypesInfo.DataBean mScience = regionTypes.get(6);
               // RegionTypeDetailsActivity.launch(getActivity(), mScience);
                break;

            case 7:
                //生活
                RegionTypesInfo.DataBean mLife = regionTypes.get(7);
               // RegionTypeDetailsActivity.launch(getActivity(), mLife);
                break;

            case 8:
                //鬼畜
                RegionTypesInfo.DataBean mKichiku = regionTypes.get(8);
            //    RegionTypeDetailsActivity.launch(getActivity(), mKichiku);
                break;

            case 9:
                //时尚
                RegionTypesInfo.DataBean mFashion = regionTypes.get(9);
             //   RegionTypeDetailsActivity.launch(getActivity(), mFashion);
                break;

            case 10:
                //广告
              //  startActivity(new Intent(getActivity(), AdvertisingActivity.class));
                break;

            case 11:
                //娱乐
                RegionTypesInfo.DataBean mRecreation = regionTypes.get(10);
              //  RegionTypeDetailsActivity.launch(getActivity(), mRecreation);
                break;

            case 12:
                //电影
                RegionTypesInfo.DataBean mMovei = regionTypes.get(11);
                RegionTypeDetailsActivity.launch(getActivity(), mMovei,itemNames[12]);
                break;

            case 13:
                //电视剧
                RegionTypesInfo.DataBean mTv = regionTypes.get(12);
               // RegionTypeDetailsActivity.launch(getActivity(), mTv);
                break;

            case 14:
                // 游戏中心
               // startActivity(new Intent(getActivity(), GameCentreActivity.class));
                break;

            default:
                break;
        }
    }
}

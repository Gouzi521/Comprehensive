package com.ma.pingan.comprehensive.api;

import com.ma.pingan.comprehensive.bean.Ranking;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public interface ApiService {

    //排行榜http://api.zhuishushenqi.com/ranking


    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     *
     * @return
     */
    @GET("/ranking/{rankingId}")
    Observable<Ranking> getRanking(@Path("rankingId") String rankingId);
}

package com.ma.pingan.comprehensive.api;

import com.ma.pingan.comprehensive.bean.BookDetail;
import com.ma.pingan.comprehensive.bean.BookListDetail;
import com.ma.pingan.comprehensive.bean.BookLists;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.bean.BooksByCats;
import com.ma.pingan.comprehensive.bean.BooksByTag;
import com.ma.pingan.comprehensive.bean.CategoryList;
import com.ma.pingan.comprehensive.bean.CategoryListLv2;
import com.ma.pingan.comprehensive.bean.ChapterRead;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.bean.Ranking;
import com.ma.pingan.comprehensive.bean.RecommendBookList;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;
import com.ma.pingan.comprehensive.bilientity.VideoDetailsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */

public interface ApiService {
    /**
     * 视频详情数据
     */
    @GET(
            "x/view?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=1206255541e2648c1badb87812458046&ts=1478349831")
    Observable<VideoDetailsInfo> getVideoDetails(@Query("aid") int aid);


    /**
     * 分区推荐
     */
    @GET(
            "x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694")
    Observable<RegionRecommendInfo> getRegionRecommends(@Query("rid") int rid);


    //小说阅读——————————————————————————————————————————————————————————————————————

    /**
     * 获取书单详情
     *
     * @return
     */
    @GET("/book-list/{bookListId}")
    Observable<BookListDetail> getBookListDetail(@Path("bookListId") String bookListId);

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param limit  20
     * @return
     */
    @GET("/book-list")
    Observable<BookLists> getBookLists(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("tag") String tag, @Query("gender") String gender);


    //排行榜http://api.zhuishushenqi.com/ranking

    /**
     * 通过作者查询书名
     *
     * @param author
     * @return
     */
    @GET("/book/accurate-search")
    Observable<BooksByTag> searchBooksByAuthor(@Query("author") String author);

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


    /**
     * 获取分类
     *
     * @return
     */
    @GET("/cats/lv2/statistics")
    Observable<CategoryList> getCategoryList();

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET("/cats/lv2")
    Observable<CategoryListLv2> getCategoryListLv2();

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET("/book/by-categories")
    Observable<BooksByCats> getBooksByCats(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);


    /**
     * 热门评论
     *
     * @param book
     * @return
     */
    @GET("/post/review/best-by-book")
    Observable<HotReview> getHotReview(@Query("book") String book);

    @GET("/book-list/{bookId}/recommend")
    Observable<RecommendBookList> getRecommendBookList(@Path("bookId") String bookId, @Query("limit") String limit);

    @GET("/book/{bookId}")
    Observable<BookDetail> getBookDetail(@Path("bookId") String bookId);

    @GET("/mix-atoc/{bookId}")
    Observable<BookMixAToc> getBookMixAToc(@Path("bookId") String bookId, @Query("view") String view);

    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead> getChapterRead(@Path("url") String url);


}
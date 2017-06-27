package com.ma.pingan.comprehensive.api;

import com.ma.pingan.comprehensive.bean.BookDetail;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.bean.BooksByCats;
import com.ma.pingan.comprehensive.bean.BooksByTag;
import com.ma.pingan.comprehensive.bean.CategoryList;
import com.ma.pingan.comprehensive.bean.CategoryListLv2;
import com.ma.pingan.comprehensive.bean.ChapterRead;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.bean.Ranking;
import com.ma.pingan.comprehensive.bean.RecommendBookList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /**
     * 通过作者查询书名
     *
     * @param author
     * @return
     */
    @GET("/book/accurate-search")
    Observable<BooksByTag> searchBooksByAuthor(@Query("author") String author);
}

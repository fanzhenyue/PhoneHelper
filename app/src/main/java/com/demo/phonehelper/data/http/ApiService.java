package com.demo.phonehelper.data.http;

import com.demo.phonehelper.bean.AppInfo;
import com.demo.phonehelper.bean.BaseBean;
import com.demo.phonehelper.bean.Category;
import com.demo.phonehelper.bean.IndexBean;
import com.demo.phonehelper.bean.LoginBean;
import com.demo.phonehelper.bean.PageBean;
import com.demo.phonehelper.bean.requestbean.LoginRequestBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/12/4.
 *
 */

public interface ApiService {

    public static final String BASE_URL ="http://112.124.22.238:8081/course_api/cniaoplay/";

     String URL ="http://112.124.22.238:8081/course_api/cniaoplay/featured?p={%22page%22:0}";

//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);


    @GET("index")
    public  Observable<BaseBean<IndexBean>> index();


    //排行榜
    @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page); //

    //游戏
    @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page); //


    //{"phone":"","password":""}
    @POST("login")
    public Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);


    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();

    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    //app应用详情
    @GET("app/{id}")
    Observable<BaseBean<AppInfo>> getAppDetail(@Path("id") int id);

//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//
//    OkHttpClient client = new OkHttpClient();
//
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }



    @FormUrlEncoded // FormBody
    @POST("login")
    public   void login2(@Field("phone") String phone);

//
//    FormBody.Builder builder = new FormBody.Builder();
//
//    builder.addEncoded("phone","");
//
//    body = builde.build();

//    Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
}

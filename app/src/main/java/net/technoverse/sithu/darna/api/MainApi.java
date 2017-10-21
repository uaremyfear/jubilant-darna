package net.technoverse.sithu.darna.api;

import net.technoverse.sithu.darna.utlis.Const;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by sithu on 6/22/17.
 */

public class MainApi {


        private static Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Const.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient());


        public static  <S> S createService(Class<S> serviceClass){
            Retrofit retrofit = builder.build();
            return retrofit.create(serviceClass);
        }

}

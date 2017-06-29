package socialboards.echidnanative.com.test.NetworkService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import socialboards.echidnanative.com.test.model.Maps;
import socialboards.echidnanative.com.test.model.User;

/**
 * Created by vali on 25/06/17.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("authenticate")
    Call<User> loginCheck(@Field("email") String first, @Field("password") String last);


    @GET("listing/Meeting/Bangalore")
        //Assumption This API is only for Bangalore otherwise we can take state name as parameter to get call
    Call<Maps> listOfLocation();
}

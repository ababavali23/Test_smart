package socialboards.echidnanative.com.test.LoginScreen;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import socialboards.echidnanative.com.test.NetworkService.APIClient;
import socialboards.echidnanative.com.test.NetworkService.LoginService;
import socialboards.echidnanative.com.test.model.Listings;
import socialboards.echidnanative.com.test.model.Maps;
import socialboards.echidnanative.com.test.model.User;

/**
 * Created by vali on 25/06/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.LoginView loginView;
    LoginContract.MapView mapView;

    public LoginPresenter(LoginContract.LoginView loginView) {

        this.loginView = loginView;
    }

    public LoginPresenter(LoginContract.MapView mapView) {

        this.mapView = mapView;
    }

    @Override
    public void mapService() {


        LoginService loginService = APIClient.getClient().create(LoginService.class);
        Call<Maps> mapsCall = loginService.listOfLocation();
        mapsCall.enqueue(new Callback<Maps>() {
            @Override
            public void onResponse(Call<Maps> call, Response<Maps> response) {

                List<Listings> location = Arrays.asList(response.body().getListings());
                if (mapView != null && response.body().getSuccess())
                    mapView.mapsList(location);
                else
                    mapView.makeToast("Something went wrong.");

            }

            @Override
            public void onFailure(Call<Maps> call, Throwable t) {

                mapView.makeToast("Something went wrong with network.");
            }
        });
    }

    @Override
    public void loginService(String userName, String passWord) {


        LoginService loginService = APIClient.getClient().create(LoginService.class);
        Call<User> userInfo = loginService.loginCheck(userName, passWord);

        userInfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (loginView != null && response.body().getSuccess())
                    loginView.startProfileActivity();
                else
                    loginView.makeToast("User not found.");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                if (loginView != null)
                    loginView.makeToast("Something went wrong !!!.");
            }
        });
    }
}

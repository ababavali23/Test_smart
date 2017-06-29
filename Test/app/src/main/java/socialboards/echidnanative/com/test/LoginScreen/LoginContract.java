package socialboards.echidnanative.com.test.LoginScreen;

import java.util.List;

import socialboards.echidnanative.com.test.model.Listings;


/**
 * Created by vali on 25/06/17.
 */

public interface LoginContract {
    interface LoginView {

        void makeToast(String message);


        void startProfileActivity();


    }

    interface MapView {

        void makeToast(String message);


        void mapsList(List<Listings> body);


    }

    interface Presenter {
        void mapService();

        void loginService(String username, String password);

    }
}

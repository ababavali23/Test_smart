package socialboards.echidnanative.com.test.LoginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import socialboards.echidnanative.com.test.MapActivity;
import socialboards.echidnanative.com.test.R;

public class MainActivity extends AppCompatActivity implements LoginContract.LoginView {

    LoginPresenter loginPresenter;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.passWord)
    EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.button_login)
    public void LoginVerify(View v) {


        if (!userName.getText().toString().trim().isEmpty()) {

            if (passWord.getText().toString().trim().isEmpty()) {

                makeToast("Password is empty");
            } else
                loginPresenter.loginService(userName.getText().toString().trim(), passWord.getText().toString().trim());

        } else
            makeToast("Username is empty");


    }


    @Override
    public void makeToast(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProfileActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


}

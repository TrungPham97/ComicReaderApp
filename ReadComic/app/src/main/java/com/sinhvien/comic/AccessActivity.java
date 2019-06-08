package com.sinhvien.comic;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class AccessActivity extends AppCompatActivity {

    private static final String TAG = AccessActivity.class.getSimpleName();
    CallbackManager callbackManager;
    LoginButton fbLoginButton;
    TextView txt_login_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());


        callbackManager = CallbackManager.Factory.create();
        fbLoginButton = findViewById(R.id.login_button);
        txt_login_email = findViewById(R.id.txt_login_email);
        fbLoginButton.setReadPermissions("email");
        //Callback for button login fb
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"======Facebook login success======");
                Log.d(TAG,"Facebook Access Token: "+ loginResult.getAccessToken().getToken());
                Toast.makeText(AccessActivity.this, "Login Facebook success.", Toast.LENGTH_SHORT).show();
                getFbInfo();
            }

            @Override
            public void onCancel() {
                Toast.makeText(AccessActivity.this, "Login Facebook Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"=======Login Facebook Error=======");
                Log.d(TAG, "Error: "+error.toString());
                Toast.makeText(AccessActivity.this, "Login Facebook Failed with"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFbInfo(){
        if (AccessToken.getCurrentAccessToken()!=null){
            GraphRequest request =
                    GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()
                            , new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(final JSONObject me, GraphResponse response) {
                                    if (me!=null){
                                        Log.i("Login: ",me.optString("name"));
                                        Log.i("ID: ",me.optString("id"));
                                        txt_login_email.setText("Hello "+me.optString("name"));
                                    }
                                }
                            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}

package com.example.week3live;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class SplashScreen extends AppCompatActivity implements LoginButtonListener {
    @Override
    public void loginclicked() {
        //finish(); or android:noHistory="true"
        startActivity(new Intent(this,AppMain.class));
    }

    public static class SplashFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.splash_layout, container, false);
            Glide.with(getActivity())
                    .load(R.drawable.loading)
                    .into((ImageView)v.findViewById(R.id.logo));
            return v;
        }
    }
    public static class LoginFragment extends Fragment {
        LoginButtonListener loginButtonListener;

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            try{
                loginButtonListener=(LoginButtonListener) context;
            }
            catch (ClassCastException e){
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v =  inflater.inflate(R.layout.login_fragment_layout, container, false);
            v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginButtonListener.loginclicked();
                }
            });
            return v;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,new SplashFragment());
        fragmentTransaction.commit();
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                Random rd = new Random(); // creating Random object
                if(rd.nextBoolean()){
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,new LoginFragment());
                    fragmentTransaction.commit();

                }else{
                    // finish();
                    startActivity(new Intent(SplashScreen.this,AppMain.class));
                }

            }
        }.start();
    }
}
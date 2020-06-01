package com.geetha.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.welcome_activity);
        tv1=findViewById (R.id.tv1);
        RetrofitInstance.get ().getAllRecipes ().enqueue (new Callback <List <Recipe>> () {
            @Override
            public void onResponse(Call <List <Recipe>> call, Response <List <Recipe>> response) {
                tv1.setText(String.valueOf (response.body ().size ()));
                Toast.makeText (WelcomeActivity.this,"Success",Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onFailure(Call <List <Recipe>> call, Throwable t) {
                Toast.makeText (WelcomeActivity.this,"Failure",Toast.LENGTH_SHORT).show ();
            }
        });

    }
}

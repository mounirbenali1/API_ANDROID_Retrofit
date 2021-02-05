package com.example.usersapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listView; //Declarer listView
    ArrayList<UserInfo> arrayList; //arrayList pour stocker les users
    UserAdapter arrayAdapter; //  arrayAdapter
    ArrayList<UserInfo> all_users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<UserInfo>();
        arrayAdapter = new UserAdapter(this, R.layout.list_adaptater_view,arrayList); //affecter notre adapter pour une autre layout
        listView.setAdapter(arrayAdapter);





    }
    public void getUser(View view){
        TextView FnameView = (TextView) findViewById(R.id.first_name);
        EditText userID = (EditText) findViewById(R.id.nombre_users);
        // Connexion à API
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceApi apiInterface = retrofit.create(InterfaceApi.class);
        Call<JsonDataHolder> call = apiInterface.getUsers( Integer.parseInt(userID.getText().toString()));
        call.enqueue(new Callback<JsonDataHolder>() {
            @Override
            public void onResponse(Call<JsonDataHolder> call, Response<JsonDataHolder> response) {
                // In case of success
                arrayList.clear();
                UserInfo usertmp =new UserInfo(response.body().getId(),response.body().getEmail(),response.body().getFname(),response.body().getLname(),response.body().getAvatar());
                arrayList.add(usertmp);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonDataHolder> call, Throwable t) {

            }
        });
    }
    public void getAllUsers(View view){
        // Connexion with API
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceApi apiInterface = retrofit.create(InterfaceApi.class);
        Call<JsonDataHolderAll> call = apiInterface.getAll();
        call.enqueue(new Callback<JsonDataHolderAll>() {
            @Override
            public void onResponse(Call<JsonDataHolderAll> call, Response<JsonDataHolderAll> response) {

                arrayList.clear();
                all_users = (ArrayList<UserInfo>) response.body().getAll();
                arrayList.addAll(all_users);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonDataHolderAll> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}
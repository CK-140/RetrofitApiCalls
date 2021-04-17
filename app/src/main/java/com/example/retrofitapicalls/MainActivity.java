package com.example.retrofitapicalls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView)findViewById(R.id.get_repo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //getPosts();
        getComments();
    }
    private void getPosts(){
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    result.setText("Code" + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for(Post post : posts){
                    String content = "";
                    content += "UserID" + post.getuId() + "\n";
                    content += "ID" + post.getId() + "\n";
                    content += "Title" + post.getTitle() + "\n";
                    content += "Body" + post.getText() + "\n";
                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }
    private void getComments(){
        Call<List<Commente>> call = jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Commente>>() {
            @Override
            public void onResponse(Call<List<Commente>> call, Response<List<Commente>> response) {
                if(!response.isSuccessful()){
                    result.setText("Code" + response.code());
                    return;
                }
                List<Commente> comments = response.body();
                for(Commente comment : comments){
                    String content = "";
                    content += "ID" + comment.getId() + "\n";
                    content += "PostId" + comment.getPostId() + "\n";
                    content += "Name" + comment.getName() + "\n";
                    content += "Email" + comment.getEmail() + "\n";
                    content += "Text"  + comment.getText() + "\n";
                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Commente>> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });

    }
}
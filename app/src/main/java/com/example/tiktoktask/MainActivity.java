package com.example.tiktoktask;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.VideoAdapter;
import com.example.model.GetVideoResponse;
import com.example.model.Video;
import com.example.retrofit.Api;
import com.example.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 view_pager;
    private List<Video>videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view_pager=findViewById(R.id.view_pager);

        videoList=new ArrayList<>();

       /* VideoModel videoModel1=new VideoModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4","title1","dec1");
        videoList.add(videoModel1);

        VideoModel videoModel2=new VideoModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4","title2","dec2");
        videoList.add(videoModel2);

        VideoModel videoModel3=new VideoModel("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4","title3","desc3");
        videoList.add(videoModel3);*/



        ApiInterface apiInterface = Api.getClient().create(ApiInterface.class);

       Call<List<GetVideoResponse>>call=apiInterface.getVideos();
       call.enqueue(new Callback<List<GetVideoResponse>>() {
           @Override
           public void onResponse(Call<List<GetVideoResponse>> call, Response<List<GetVideoResponse>> response) {
               Toast.makeText(MainActivity.this, "hii", Toast.LENGTH_SHORT).show();

               Toast.makeText(MainActivity.this, ""+response.body().get(0).getCategories().get(0).getName(), Toast.LENGTH_SHORT).show();

               videoList=response.body().get(0).getCategories().get(0).getVideos();

               view_pager.setAdapter(new VideoAdapter(videoList));

               Log.e("listsize",""+videoList.size());
           }

           @Override
           public void onFailure(Call<List<GetVideoResponse>> call, Throwable t) {

               Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });



    }
}
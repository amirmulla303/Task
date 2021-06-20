package com.example.adapter;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktoktask.R;
import com.example.model.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video>videoList;

    public VideoAdapter(List<Video> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(videoList.get(position));

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private VideoView videoView;
        private TextView title,desc;
        private ProgressBar progress_bar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView=itemView.findViewById(R.id.video_view);
            title=itemView.findViewById(R.id.tv_title);
            desc=itemView.findViewById(R.id.tv_desc);
            progress_bar=itemView.findViewById(R.id.progress_bar);

        }

        void setData(Video videoModel){
            videoView.setVideoPath(videoModel.getSources().get(0));
            title.setText(videoModel.getTitle());
            desc.setText(videoModel.getDescription());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    progress_bar.setVisibility(View.GONE);
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}

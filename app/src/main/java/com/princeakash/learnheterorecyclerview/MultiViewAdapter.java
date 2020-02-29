package com.princeakash.learnheterorecyclerview;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MultiViewAdapter extends RecyclerView.Adapter {

    private ArrayList<Model> dataList;
    Context mContext;
    int total_types;
    MediaPlayer mediaPlayer;
    private Boolean fabStateVolume = false;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            case Model.AUDIO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_type, parent, false);
                return new AudioTypeViewHolder(view);
            case Model.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
                return new ImageTypeViewHolder(view);
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
                return new TextTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final Model object = dataList.get(position);
        if(object != null){
            switch(object.modelType){
                case Model.AUDIO_TYPE:
                    ((AudioTypeViewHolder) holder).textView.setText(object.modelText);
                    ((AudioTypeViewHolder) holder).fab.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            if(fabStateVolume) {
                                if(mediaPlayer.isPlaying()){
                                    mediaPlayer.stop();
                                }
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.mipmap.volume);
                                fabStateVolume = false;
                            }
                            else{
                                mediaPlayer = MediaPlayer.create(mContext, object.modelData);
                                mediaPlayer.setLooping(true);
                                mediaPlayer.start();
                                ((AudioTypeViewHolder) holder).fab.setImageResource(R.mipmap.volume);
                                fabStateVolume = true;
                            }
                        }
                    });
                    break;
                case Model.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).textView.setText(object.modelText);
                    ((ImageTypeViewHolder) holder).imageView.setImageResource(object.modelData);
                    break;
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).textView.setText(object.modelText);
                    break;
            }
        }
    }

    public int getItemViewType(int position) {
        switch(dataList.get(position).modelType){
            case Model.AUDIO_TYPE:
                return Model.AUDIO_TYPE;
            case Model.IMAGE_TYPE:
                return Model.IMAGE_TYPE;
            case Model.TEXT_TYPE:
                return Model.TEXT_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        CardView cardView;
        public TextTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.textTypeCardView);
            this.textView = itemView.findViewById(R.id.textTypeTextView);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CardView cardView;
        ImageView imageView;

        public ImageTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.imageTypeTextView);
            this.cardView = itemView.findViewById(R.id.imageTypeCardView);
            this.imageView = itemView.findViewById(R.id.background);
        }
    }

    public static class AudioTypeViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        FloatingActionButton fab;
        CardView cardView;

        public AudioTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.audioTypeTextView);
            this.fab = itemView.findViewById(R.id.audioTypeFab);
            this.cardView = itemView.findViewById(R.id.audioTypeCardView);
        }
    }

    public MultiViewAdapter(ArrayList<Model> data, Context context){
        this.dataList = data;
        this.mContext = context;
    }
}

package com.example.streammusicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter <SongAdapter.ViewHolder> {


    private List<Song> SongList;
    private OnNoteListener MonNoteListener;
    SongAdapter(List<Song> list,OnNoteListener onNoteListener)
    { this.SongList = list;
        this.MonNoteListener = onNoteListener;}


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Title,Artist;ImageView Thumb;
        OnNoteListener onNoteListener;
        public ViewHolder(View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.title);
            Artist = (TextView) itemView.findViewById(R.id.artist);
            Thumb = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.song_element, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView,MonNoteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = SongList.get(position);
        holder.Title.setText(song.getSongName());
        holder.Artist.setText(song.getSongArtist());
        Picasso.get().load(song.getImageUrl()).resize(70,60).into(holder.Thumb);
    }

    @Override
    public int getItemCount() {
        return SongList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

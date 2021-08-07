package com.example.streammusicapp;

public class Song {
    private String imageUrl,songArtist,songName,songUrl,type;

    public Song() { }
    public Song(String imageUrl,String songArtist,String songName,String songUrl,String type)
    {
        this.setSongName(songName);
        this.setSongUrl(songUrl);
        this.setType(type);
        this.setImageUrl(imageUrl);
        this.setSongArtist(songArtist);
    }
    public String getSongArtist() {
        return songArtist;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getSongName() {
        return songName;
    }


    public String getSongUrl() {
        return songUrl;
    }


    public String getType(){return type;}

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }
    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public void setImageUrl(String ImageUrl) {
        this.imageUrl = ImageUrl;
    }
    public void setType(String type){this.type = type;}
}

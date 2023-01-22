package com.eximius.annimonclient.utils;
import android.media.MediaPlayer;
import android.content.Context;
import android.net.Uri;

public class MiniFM {
    
    private MediaPlayer player;
    
    
    public MiniFM(Context context){
        try{
        this.player=MediaPlayer.create(context,Uri.parse("http://online-news.radioplayer.ua/RadioNews"));
        }catch(Exception e){}
    }
    
    public void play(){
        player.prepareAsync();
        player.start();
    }
    
    public void stop(){
        player.stop();
    }
    
    public void pause(){
        player.pause();
    }
    
}

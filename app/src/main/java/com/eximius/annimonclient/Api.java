package com.eximius.annimonclient;

import com.eximius.annimonclient.data.Message;
import com.eximius.annimonclient.data.News;
import com.eximius.annimonclient.data.User;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.eximius.annimonclient.data.PhotoAlbum;
import androidx.core.math.MathUtils;
import com.eximius.annimonclient.data.Photo;

public class Api {


    public static ArrayList<News> getNews(int page) {
		//title - h2.statname
		//author - div.statmenu small
		//ArrayList<News> allNews=new ArrayList<News>();
		try {
			ArrayList<News> allNews=new ArrayList<News>();
			Document doc= Jsoup.connect("https://annimon.com/str/news.php?start=" + page).get();
			Elements statNames=doc.select("h2.statname");
			Elements authors=doc.select("div.statmenu small");
			Elements message=doc.select("div.statmenu");

			for (int i = 0; i < statNames.size(); i++) {
				News news=new News();
				news.setId(i);
				news.setTitle(statNames.get(i).toString());
				news.setAuthor(authors.get(i).toString());
				news.setText(message.get(i).toString()
							 .replace(news.getTitle(), "")
							 .replace(news.getAuthor(), ""));
				allNews.add(news);
			}
			return allNews;
		} catch (Exception e) {return new ArrayList<News>();}


		//return allNews;
	}

	public static ArrayList<Message> getMessages() {
		ArrayList<Message> allMessages=new ArrayList<Message>();
		for (int i = 0; i < 5; i++) {
			Message msg=new Message();
			msg.setSender("User " + i);
			msg.setMessage("User message.." + i);
			msg.setDateSend("14.12.2022 / 10:15");
			allMessages.add(msg);
		}
		return allMessages;
	}

	public static ArrayList<Message> getGuestBookPosts() {
        //sender id -
        //sender ava -
        //sender - 
        //message -
		//date send - div.list2 small
        
		ArrayList<Message> allPosts=new ArrayList<Message>();

		for (int i = 0; i < 10; i++) {
            Message msg=new Message();
            msg.setId(i);
            msg.setSenderAvaUrl("");
            msg.setSender("Гость " + i);
            msg.setMessage("Сообщение от гостя " + i);
            msg.setDateSend("16.11.2022 / 18:54");
            allPosts.add(msg);
        }
        return allPosts;
	}

	public static ArrayList<User> getUsers() {
        //div.list2 td b

        try {
            ArrayList<User> allUsers=new ArrayList<User>();
            Document doc=Jsoup.connect("https://annimon.com/str/users.php?sort=id&start=4520").get();
            Elements users1=doc.select("div.list1");
            Elements users2=doc.select("div.list2");
            for (int i=0;i < users1.size();i++) {
                User user=new User();
                User user2=new User();
                //1
                user.setId(Integer.valueOf(users1.get(i).select("a").get(1).text()));
                user.setNick(users1.get(i).select("a").get(0).text());

                if (users1.get(i).select("span").text().equalsIgnoreCase("[On]")) {
                    user.setIsOnline(true);
                } else if (users1.get(i).select("span").text().equalsIgnoreCase("[Off]")) {
                    user.setIsOnline(false);
                }
                user.setStatus(users1.get(i).select("div.status").text());

                //2
                user2.setId(Integer.valueOf(users2.get(i).select("a").get(1).text()));
                user2.setNick(users2.get(i).select("a").get(0).text());

                if (users2.get(i).select("span").text().equalsIgnoreCase("[On]")) {
                    user2.setIsOnline(true);
                } else if (users2.get(i).select("span").text().equalsIgnoreCase("[Off]")) {
                    user2.setIsOnline(false);
                }
                user2.setStatus(users2.get(i).select("div.status").text());


                allUsers.add(user);
                allUsers.add(user2);
            }
            return allUsers;
        } catch (Exception e) {return new ArrayList<User>();}

	}
    
    public static ArrayList<PhotoAlbum> getPhotoAlbums(){
        
        try{
            ArrayList<PhotoAlbum> albums=new ArrayList<PhotoAlbum>();
            //Document doc=Jsoup.connect("https://annimon.com/files/photo/1.jpg").get();
            for(int i=0;i<50;i++){
                PhotoAlbum album=new PhotoAlbum();
                album.setId(i);
                album.setName("Photo album "+i);
                album.setAuthor("User "+i);
                album.setNumPhotos((int)(Math.random()*50));
                albums.add(album);
            }
            return albums;
        }catch(Exception e){return new ArrayList<PhotoAlbum>();}
    }
    
    public static ArrayList<Photo> getPhotos(){
        try{
            ArrayList<Photo> photos=new ArrayList<Photo>();
            
            for (int i = 0; i < 50; i++) {
                Photo photo=new Photo();
                photo.setPhoto("");
                photo.setImg("");
                photos.add(photo);
            }
            return photos;
        }catch(Exception e){return new ArrayList<Photo>();}
    }

}

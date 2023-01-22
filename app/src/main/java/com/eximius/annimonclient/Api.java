package com.eximius.annimonclient;

import com.eximius.annimonclient.data.Article;
import com.eximius.annimonclient.data.Message;
import com.eximius.annimonclient.data.News;
import com.eximius.annimonclient.data.Photo;
import com.eximius.annimonclient.data.PhotoAlbum;
import com.eximius.annimonclient.data.User;
import com.eximius.annimonclient.utils.WebRequest;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Api {


    public static ArrayList<News> getNews(int page) {

		try {
			ArrayList<News> allNews=new ArrayList<News>();
			Document doc= Jsoup.connect("https://annimon.com/str/news.php?start=" + page).get();
			Elements statNames=doc.select("h2.statname");
			Elements authors=doc.select("div.statmenu small");
			Elements message=doc.select("div.statmenu");

			for (int i = 0; i < statNames.size(); i++) {
				News news=new News();
				news.setId(i);
				news.setTitle(statNames.get(i).text());
				news.setAuthor(authors.get(i).text());
				news.setText(message.get(i).toString()
							 .replace(news.getTitle(), "")
							 .replace(news.getAuthor(), ""));
				allNews.add(news);
			}
			return allNews;
		} catch (Exception e) {return new ArrayList<News>();}
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

	public static ArrayList<User> getUsers(int page) {

        try {
            ArrayList<User> allUsers=new ArrayList<User>();
            Document doc=Jsoup.connect("https://annimon.com/str/users.php?sort=id&start=" + page).get();
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

    public static ArrayList<PhotoAlbum> getPhotoAlbums(int page) {

        try {
            ArrayList<PhotoAlbum> albums=new ArrayList<PhotoAlbum>();
            Document doc=Jsoup.connect("https://annimon.com/albums/?start=" + page).get();
            Elements albums1=doc.select("div.list1");
            Elements albums2=doc.select("div.list2");

            for (int i=0;i < albums1.size();i++) {
                PhotoAlbum album=new PhotoAlbum();
                album.setId(Integer.parseInt(albums1.get(i).select("a").get(0).attr("href").replace("./?act=album&id=", "")));
                album.setName(albums1.get(i).select("a").get(1).text());
                album.setAuthor(albums1.get(i).select("a").get(2).text());
                album.setNumPhotos(Integer.parseInt(albums1.get(i).select("span[class=gray]").text().split(" ")[1]));
                album.setUrlPhoto("https://annimon.com/albums/" + albums1.select("img").attr("src"));

                PhotoAlbum album2=new PhotoAlbum();
                album2.setId(Integer.parseInt(albums2.get(i).select("a").get(0).attr("href").replace("./?act=album&id=", "")));
                album2.setName(albums2.get(i).select("a").get(1).text());
                album2.setAuthor(albums2.get(i).select("a").get(2).text());
                album2.setNumPhotos(Integer.parseInt(albums2.get(i).select("span[class=gray]").text().split(" ")[1]));
                album2.setUrlPhoto("https://annimon.com/albums/" + albums2.select("img").attr("src"));

                albums.add(album);
                albums.add(album2);
            }
            return albums;
        } catch (Exception e) {return new ArrayList<PhotoAlbum>();}
    }

    public static ArrayList<Photo> getPhotos(int id) {
        try {
            ArrayList<Photo> photos=new ArrayList<Photo>();
            WebRequest wr=new WebRequest();
            String json=wr.makeWebServiceCall("https://annimon.com/json/album/list_photos?album_id=" + id, WebRequest.GET);

            if (json != null) {
                JSONObject rootJson = new JSONObject(json);
                JSONArray photosJson =  rootJson.getJSONArray("photos");

                for (int i = 0; i < photosJson.length(); i++) {
                    JSONObject o=photosJson.getJSONObject(i);
                    Photo photo=new Photo();
                    photo.setId(o.getInt("id"));
                    photo.setName(o.getString("name"));
                    photo.setPhoto(o.getString("thumb").replace("http", "https"));
                    photo.setPhoto(o.getString("photo").replace("http", "https"));
                    photo.setText(o.getString("text"));
                    photo.setTime(o.getLong("time"));
                    photos.add(photo);
                }
            }
            return photos;
        } catch (Exception e) {return new ArrayList<Photo>();}
    }

    public static ArrayList<Article> getAuthorArticles() {
        try {
            ArrayList<Article> articles=new ArrayList<Article>();
            for (int i=0;i < 10;i++) {
                Article article=new Article();
                article.setId(i);
                article.setTitle("Title " + i);
                article.setText("Some text ..." + i);
                article.setLikes((int)(Math.random() * 10));
                article.setAuthor("User" + (int)(Math.random() * 10));
                article.setTime("18.01.22 / 22:31");
                articles.add(article);
            }
            return articles;
        } catch (Exception e) {return new ArrayList<Article>();}
    }
}

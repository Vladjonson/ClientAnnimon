package com.eximius.annimonclient;

import com.eximius.annimonclient.data.Article;
import com.eximius.annimonclient.data.Message;
import com.eximius.annimonclient.data.News;
import com.eximius.annimonclient.data.Photo;
import com.eximius.annimonclient.data.PhotoAlbum;
import com.eximius.annimonclient.data.User;
import com.eximius.annimonclient.data.forum.Post;
import com.eximius.annimonclient.data.forum.Section;
import com.eximius.annimonclient.data.forum.SubSection;
import com.eximius.annimonclient.data.forum.Topic;
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



	//forum


	//get sections
	public static ArrayList<Section> forumGetSections() {
		try {
			ArrayList<Section> sections=new ArrayList<Section>();

			WebRequest wr=new WebRequest();
            String json=wr.makeWebServiceCall("https://annimon.com/json/forum/get_sections", WebRequest.GET);


			//String json=Jsoup.connect("https://annimon.com/json/forum/get_sections").get().toString();


			//
			if (json != null) {
				JSONObject rootJson=new JSONObject(json);
				JSONArray jSections=rootJson.getJSONArray("sections");

				for (int i=0;i < jSections.length();i++) {
					//if(jSections.getJSONObject(i).getInt("hidden")!=1){
					Section section=new Section();
					section.setId(jSections.getJSONObject(i).getInt("id"));
					section.setText(jSections.getJSONObject(i).getString("text"));
					section.setHiden(jSections.getJSONObject(i).getInt("hidden"));
					section.setSubsections(forumGetSubSections(jSections.getJSONObject(i).getJSONArray("subsections")));
					sections.add(section);
					//}
				}
			}
			//
			return sections;
		} catch (Exception e) {return new ArrayList<Section>();}
	}

	//get subsections
	public static ArrayList<SubSection> forumGetSubSections(JSONArray j) {
		try {
			ArrayList<SubSection> subSecrions=new ArrayList<SubSection>();
			for (int i=0;i < j.length();i++) {
				//if(j.getJSONObject(i).getInt("hidden")!=1){
				SubSection subSection=new SubSection();
				subSection.setId(j.getJSONObject(i).getInt("id"));
				subSection.setText(j.getJSONObject(i).getString("text"));
				subSection.setHiden(j.getJSONObject(i).getInt("hidden"));
				subSecrions.add(subSection);
				//	}
			}
			return subSecrions;
		} catch (Exception e) {return new ArrayList<SubSection>();}
	}

	//get topics
	public static ArrayList<Topic> forumGetTopics(int section, int page) {
		try {
			ArrayList<Topic> topics=new ArrayList<Topic>();
			WebRequest wr=new WebRequest();
            String json=wr.makeWebServiceCall("https://annimon.com/json/forum/get_topics?section=" + section + "limit=10&start=" + page, WebRequest.GET);

			if (json != null) {
				JSONObject rootJson=new JSONObject(json);
				JSONArray jTopics=rootJson.getJSONArray("topics");

				for (int i = 0; i < jTopics.length(); i++) {
					Topic topic=new Topic();
					topic.setId(jTopics.getJSONObject(i).getInt("topic"));
					topic.setTitle(jTopics.getJSONObject(i).getString("title"));
					topic.setIsClosed(jTopics.getJSONObject(i).getInt("is_closed"));
					topic.setTime(jTopics.getJSONObject(i).getLong("time"));
					topics.add(topic);
                }
			}

			return topics;
		} catch (Exception e) {return new ArrayList<Topic>();}
	}

	//get posts
	public static ArrayList<Post> forumGetPosts(int topic, int page) {
		try {
			ArrayList<Post> posts=new ArrayList<Post>();
			WebRequest wr=new WebRequest();
            String json=wr.makeWebServiceCall("https://annimon.com/json/forum/get_posts?topic=" + topic + "&start=" + page + "&limit=10&html=1", WebRequest.GET);

			if (json != null) {
				JSONObject rootJson=new JSONObject(json);
				JSONArray jTposts=rootJson.getJSONArray("posts");

				for (int i = 0; i < jTposts.length(); i++) {
					Post post=new Post();
					post.setUser(jTposts.getJSONObject(i).getString("user"));
					post.setUserId(jTposts.getJSONObject(i).getInt("user_id"));
					post.setText(jTposts.getJSONObject(i).getString("text"));
					post.setTime(jTposts.getJSONObject(i).getLong("time"));
					post.setMessageId(jTposts.getJSONObject(i).getInt("message_id"));
					post.setReplyToId(jTposts.getJSONObject(i).getInt("reply_to_id"));
					posts.add(post);
                }
			}
			return posts;
		} catch (Exception e) {return new ArrayList<Post>();}
	}

}

package com.eximius.annimonclient.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.eximius.annimonclient.MainActivity;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.ItemModel;
import com.eximius.annimonclient.data.User;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class UserProfile extends Fragment {

	private ImageView userAva;
	private TextView userNick;
	private TextView userStatusOnline;
	private ListView lv;
	private User user;
    private ArrayList<ItemModel> itemsList = new ArrayList<>();

	public UserProfile(User user) {
		this.user = user;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.fragmentUserListView);
		userAva = view.findViewById(R.id.fragmentUserAva);
		userNick = view.findViewById(R.id.fragmentUserNickName);
		userStatusOnline = view.findViewById(R.id.fragmentUserStatusOnline);

        new GetUser().execute();

        Glide.with(getContext())
            .load("https://annimon.com/files/photo/" + user.getId() + ".jpg")
            .centerInside()
            .placeholder(R.drawable.no_ava)
            .into(userAva);

        userNick.setText(user.getNick());
        if (user.isOnline()) {
            userStatusOnline.setText("в сети");
        } else {
            userStatusOnline.setText("был в сети " + user.getLastTimeOnline());
        }
	}


	public void loadUserData() {
        try {
            Document doc=Jsoup.connect("https://annimon.com/user/" + user.getNick().toLowerCase()).get();
            String data=doc.select("div.maintxt").toString();
            user.setName(doc.getElementsByTag("meta").get(9).attr("content"));
        } catch (Exception e) {}
	}

	private ArrayList sortAndAddSections(ArrayList<ItemModel> itemList) {

        ArrayList<ItemModel> tempList = new ArrayList<>();
        //First we sort the array
        //Collections.sort(itemList);

        //Loops thorugh the list and add a section before each sectioncell start
        String header = "";
        for (int i = 0; i < itemList.size(); i++) {
            //If it is the start of a new section we create a new listcell and add it to our array
            if (!(header.equals(itemList.get(i).getItemHeader()))) {
                ItemModel sectionCell = new ItemModel(null, null, itemList.get(i).getItemHeader());
                sectionCell.setToSectionHeader();
                tempList.add(sectionCell);
                header = itemList.get(i).getItemHeader();
            }
            tempList.add(itemList.get(i));
        }
        return tempList;
    }

	public class ListAdapter extends ArrayAdapter {

        LayoutInflater inflater;
        public ListAdapter(Context context, ArrayList items) {
            super(context, 0, items);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ItemModel cell = (ItemModel) getItem(position);

            //If the cell is a section header we inflate the header layout
            if (cell.isSectionHeader()) {
                v = inflater.inflate(R.layout.user_section_header, null);

                v.setClickable(false);

                TextView header = (TextView) v.findViewById(R.id.userSectionHeader);
                header.setText(cell.getItemHeader());
            } else {
                v = inflater.inflate(R.layout.user_row_item, null);
                TextView tvText = (TextView) v.findViewById(R.id.userRowItemText);
                TextView tvDescription = (TextView) v.findViewById(R.id.userRowItemDescription);

                tvText.setText(cell.getItemText());
                tvDescription.setText(cell.getItemDescription());
            }
            return v;
        }
    }

	private ArrayList<ItemModel>  getItems() {

        ArrayList<ItemModel> items = new ArrayList<>();

        items.add(new ItemModel("Репутация", "" + user.getReputation(), "Репутация"));
		//items.add(new ItemModel("Всего голосов","rDescription2","Reputation"));
		items.add(new ItemModel("Бинов", "" + user.getBins(), "Репутация"));

		items.add(new ItemModel("Имя", user.getName(), "Личные данные"));
		items.add(new ItemModel("Дата рождения", user.getBirthDay(), "Личные данные"));
		items.add(new ItemModel("Город", user.getCity(), "Личные данные"));
		items.add(new ItemModel("О себе", user.getAbout(), "Личные данные"));
		items.add(new ItemModel("Телефон", user.getPhone(), "Личные данные"));
		items.add(new ItemModel("E-mail", user.getMail(), "Личные данные"));
		items.add(new ItemModel("Сайт", user.getSite(), "Личные данные"));

		items.add(new ItemModel("Сообщений на форуме", "" + user.getCountForumMessages(), "Активность"));
		items.add(new ItemModel("Тем на форуме", "" + user.getCountForumThemes(), "Активность"));
		items.add(new ItemModel("Файлов", "" + user.getCountFiles(), "Активность"));
		items.add(new ItemModel("Сообщений в гостевой", "" + user.getCountGuestMessages(), "Активность"));
		items.add(new ItemModel("Коментариев", "" + user.getCountComents(), "Активность"));
		items.add(new ItemModel("Нарушения", "" + user.getCountFailRules(), "Активность"));

		items.add(new ItemModel("Зарегестрирован", user.getDateRegistration(), "Инфо"));
		items.add(new ItemModel("Пробыл на сайте", user.getTimeOnline(), "Инфо"));

        return  items;
    }

    private class GetUser extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((MainActivity)getActivity()).showProgress();
        }



        @Override
        protected Void doInBackground(Void[] p1) {
            loadUserData();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            itemsList = sortAndAddSections(getItems());
            ListAdapter adapter = new ListAdapter(getContext(), itemsList);
            lv.setAdapter(adapter);

			((MainActivity)getActivity()).hideProgress();
        }
    }
}

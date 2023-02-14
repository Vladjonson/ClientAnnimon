package com.eximius.annimonclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.forum.Section;
import com.eximius.annimonclient.data.forum.SubSection;
import java.util.ArrayList;

public class ForumAdapter extends BaseExpandableListAdapter {

	ArrayList<Section> sections;
	Context context;

	public ForumAdapter(Context c, ArrayList<Section> s) {
		this.context = c;
		this.sections = s;
	}

	@Override
	public int getGroupCount() {
		return sections.size();
	}

	@Override
	public int getChildrenCount(int position) {
		return sections.get(position).getSubsections().size();
	}

	@Override
	public Section getGroup(int position) {
		return sections.get(position);
	}

	@Override
	public SubSection getChild(int groupPos, int childPos) {
		return getGroup(groupPos).getSubsections().get(childPos);
	}

	@Override
	public long getGroupId(int p1) {
		return 0;
	}

	@Override
	public long getChildId(int p1, int p2) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int position, boolean p2, View view, ViewGroup parent) {
		if (view == null) {
			view = ((LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.forum_list_item, parent, false);
		}
		TextView tv=view.findViewById(R.id.forum_list_itemTextView);
		tv.setText(getGroup(position).getText());
		return view;
	}

	@Override
	public View getChildView(int posGroup, int posChild, boolean p3, View view, ViewGroup parent) {
		if (view == null) {
			view = ((LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.forum_list_sub_item, parent, false);
		}
		TextView tv=view.findViewById(R.id.forum_list_sub_itemTextView);
		tv.setText(getChild(posGroup, posChild).getText());
		/*if(getChild(posGroup,posChild).getHiden()==1){
		 ImageView icon=view.findViewById(R.id.forum_list_sub_itemImageView);
		 icon.setImageResource(R.drawable.ic_lock);
		 icon.setColorFilter(Color.RED);
		 }*/
		return view;
	}

	@Override
	public boolean isChildSelectable(int p1, int p2) {
		return true;
	}

}

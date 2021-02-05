package com.example.usersapi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<UserInfo> {
    private static final String TAG = "UsersListAdapter";
    private Context mContext;
    private ArrayList<UserInfo> users;
    private int mResource;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserInfo> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
        this.users = objects;
    }

    @Override
    public int getCount() {

        return users.size();

    }

    @Nullable
    @Override
    public UserInfo getItem(int position) {
        return users.get(position);
    } //Return the item in the giving position

    @Override
    public int getPosition(@Nullable UserInfo item) {
        return users.indexOf(item);
    } //To get the position of certain item

    @Override
    public long getItemId(int position) {
        return position;
    } //get position by itemID

    public View getView(int position, View convertView, ViewGroup parents){ //Get the view in a given position
        if ( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource,parents,false);
            // Inflate our layer from the custom context and using our ressources
        }

        TextView Id = (TextView) convertView.findViewById(R.id.Id_user); //For the labels in our application
        TextView Fname = (TextView) convertView.findViewById(R.id.first_name);
        TextView Lname = (TextView) convertView.findViewById(R.id.last_name);
        ImageView Avatar= (ImageView) convertView.findViewById(R.id.image);
        TextView Email= (TextView) convertView.findViewById(R.id.Email);
        Id.setText(getItem(position).getId_str()); //Set the text for each label
        Fname.setText(getItem(position).getFirst_name());
        Lname.setText(getItem(position).getLast_name());
        Picasso.with(mContext).load(getItem(position).getAvatar().toString()).into(Avatar);
        Email.setText(getItem(position).getEmail().toString());


        return convertView;
    }

}
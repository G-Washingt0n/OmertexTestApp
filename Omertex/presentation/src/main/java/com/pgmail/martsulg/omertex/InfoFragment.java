package com.pgmail.martsulg.omertex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 25.10.2017.
 */

public class InfoFragment extends Fragment {

    private String photoUrl;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String website;
    private ImageView image;
    private TextView userName;
    private TextView userSurname;
    private TextView userEmail;
    private TextView userPhone;
    private TextView userWebsite;


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.photoUrl = (String) args.get(StartActivity.URL_KEY);
        int position = args.getInt(StartActivity.LIST_KEY);
        this.name = StartActivity.elementsModels.get(position).getName();
        this.surname = StartActivity.elementsModels.get(position).getSurname();
        this.email = StartActivity.elementsModels.get(position).getEmail();
        this.phone = StartActivity.elementsModels.get(position).getPhone();
        this.website = StartActivity.elementsModels.get(position).getWebsite();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        image = (ImageView) root.findViewById(R.id.userImage);
        Picasso.with(root.getContext())
                .load(photoUrl)
                .into(image);
        userName = (TextView) root.findViewById(R.id.userName);
        userName.setText(name);
        userSurname = (TextView) root.findViewById(R.id.userSurname);
        userSurname.setText(surname);
        userEmail = (TextView) root.findViewById(R.id.userEmail);
        userEmail.setText(email);
        userPhone = (TextView) root.findViewById(R.id.userPhone);
        userPhone.setText(phone);
        userWebsite = (TextView) root.findViewById(R.id.userWebsite);
        userWebsite.setText(website);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

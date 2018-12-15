package com.example.exitpoll.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exitpoll.R;
import com.example.exitpoll.model.VoteItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VoteListAdapter extends ArrayAdapter<VoteItem> {


    private List<VoteItem> VoteItemList;
    private Context Contexts;
    private int Resources;

    public VoteListAdapter(@NonNull Context context,
                            int resource,
                            @NonNull List<VoteItem> phoneItemList) {
        super(context, resource, phoneItemList);
        this.Contexts = context;
        this.Resources = resource;
        this.VoteItemList = phoneItemList;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) Contexts.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(Resources, parent, false);

        TextView numberTextView = view.findViewById(R.id.ResultVote);
        ImageView imageView = view.findViewById(R.id.image);

        VoteItem voteItem = VoteItemList.get(position);
        int number = voteItem.num;
        String filename = voteItem.image;

        numberTextView.setText(String.valueOf(number));

        AssetManager am = Contexts.getAssets();
        try {
            InputStream is = am.open(filename);
            Drawable drawable = Drawable.createFromStream(is, "");
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {

            e.printStackTrace();
        }

        return view;
    }

}

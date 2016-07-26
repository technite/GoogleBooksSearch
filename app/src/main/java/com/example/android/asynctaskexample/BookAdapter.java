package com.example.android.asynctaskexample;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.asynctaskexample.Book;
import com.example.android.asynctaskexample.R;

import java.util.ArrayList;

/**
 * Created by Daniel on 7/18/2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books){
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Book currentBook = getItem(position);

        TextView bookTitle = (TextView) listItemView.findViewById(R.id.book_title);
        bookTitle.setText(currentBook.getmTitle());

        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.book_author);
        bookAuthor.setText(currentBook.getmAuthor());

        ImageView imageview = (ImageView) listItemView.findViewById(R.id.book_thumbnail);

        if(currentBook.hasImage()) {
            imageview.setVisibility(View.VISIBLE);
        }
        else {
            imageview.setVisibility(View.GONE);
        }

        return listItemView;
    }
}

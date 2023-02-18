package com.serkanemek.garrybarry.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.serkanemek.garrybarry.R;

import java.util.ArrayList;

public class DataClassForAdapter extends ArrayAdapter<String> {

    private final ArrayList<String> point;
    private final ArrayList<Integer> id;
    private final Activity context;
    Typeface typeface;

    public DataClassForAdapter(ArrayList<String> point,ArrayList<Integer> id, Activity context){
        super(context, R.layout.custom_view, point);
        this.point = point;
        this.id = id;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view, null,true);
        TextView pointText = customView.findViewById(R.id.customView_point_text);
        TextView idText = customView.findViewById(R.id.customView_point_text2);
        pointText.setText(point.get(position));
        idText.setText(id.get(position) + "");

        setFonts(context);
        pointText.setTypeface(typeface);
        idText.setTypeface(typeface);

        return customView;
    }

    public void setFonts(Context context){
        typeface = Typeface.createFromAsset(context.getAssets(),"fonts/FredokaOne-Regular.ttf");

    }
}

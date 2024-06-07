package com.example.dcu_image_viwer;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public abstract class DetailImageAdapter extends BaseAdapter implements View.OnCapturedPointerListener {

    private Context context;
    private LayoutInflater inflater;

    public DetailImageAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 30; // 30 images
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.detail_image_item, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        int resourceId = context.getResources().getIdentifier("natural" + (position + 1), "drawable", context.getPackageName());
        imageView.setImageResource(resourceId);

        return view;
    }
}

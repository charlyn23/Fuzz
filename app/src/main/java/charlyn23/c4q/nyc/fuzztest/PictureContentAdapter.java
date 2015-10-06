package charlyn23.c4q.nyc.fuzztest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlynbuchanan on 10/6/15.
 */
public class PictureContentAdapter extends ArrayAdapter<JSONObject> {
    Context context = getContext();
    ArrayList<JSONObject> items = DataGetter.getItems();
    static JSONObject fullContentItem;
    public static ViewHolder holder;
    HomeActivity homeActivity = new HomeActivity();
    android.webkit.WebViewFragment webViewFragment = new android.webkit.WebViewFragment();

    public PictureContentAdapter(Context context, int resource, List<JSONObject> items) {
        super(context, resource, items);
        this.context = context;

    }

    static class ViewHolder {
        ImageView imageView;
    }

    public View getView(int position,View view,ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.pic_list, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView)view.findViewById(R.id.imageView);

            view.setTag(holder);
        }
        else
            holder = (ViewHolder)view.getTag();

        //set imageview
        for (int i = 0; i < items.size(); i++) {
            JSONObject fullContentItem = getItem(position);
            try {
                String type = fullContentItem.getString("type");
                String data = fullContentItem.getString("data");


                if (type.equals("image")) {
                    Picasso.with(context)
                            .load(data)
                            .into(holder.imageView);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return view;
    }
}

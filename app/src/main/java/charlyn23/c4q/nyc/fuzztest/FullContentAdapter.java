package charlyn23.c4q.nyc.fuzztest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlynbuchanan on 10/4/15.
 */
public class FullContentAdapter extends ArrayAdapter<JSONObject> {

    Context context;
    ArrayList<JSONObject> items = DataGetter.getItems();
    static JSONObject fullContentItem;
    public static ViewHolder holder;
    View.OnClickListener textListener;
    HomeActivity homeActivity = new HomeActivity();
    WebViewFragment webViewFragment = new WebViewFragment();


    public FullContentAdapter(Context context, int resource, List<JSONObject> items) {
        super(context, R.layout.mylist, DataGetter.getItems());
        this.context = context;




    }

    static class ViewHolder {
        TextView id;
        TextView data;
        TextView date;
        ImageView icon;
    }

    public View getView(int position,View view,ViewGroup parent) {
        ViewHolder holder;

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService
//                (Activity.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.mylist, parent, false);
            holder = new ViewHolder();
            holder.data = (TextView)view.findViewById(R.id.data);
            holder.date = (TextView)view.findViewById(R.id.date);
            holder.id = (TextView)view.findViewById(R.id.id);
            holder.icon = (ImageView)view.findViewById(R.id.icon);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder)view.getTag();

        //set textviews and imageview
        for (int i = 0; i < items.size(); i++) {
            JSONObject fullContentItem = getItem(position);
            try {
                String id = fullContentItem.getString("id");
                holder.id.setText("ID: " + id);

                String data = fullContentItem.getString("data");
                holder.data.setText("Data: " + data);

                String date = fullContentItem.getString("date");
                holder.date.setText("Date: " + date);

                String type = fullContentItem.getString("type");

                if (type.equals("image")) {
                    Picasso.with(context)
                            .load(data)
                            .into(holder.icon);
                }
                else if (type.equals("text")){
                    homeActivity.addShowHideListener(holder, webViewFragment);



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    public void loadImage() throws JSONException {
        Picasso.with(getContext())
                .load(fullContentItem.getString("data"))
                .into(holder.icon);
    }



}

package charlyn23.c4q.nyc.fuzztest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;


public class FullContentFragment extends Fragment {

    public static View rootView;
    ListView fullContentListView;
    ArrayList<JSONObject> items;
    JSONObject item;


    public FullContentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<JSONObject> items = DataGetter.getItems();

        rootView = inflater.inflate(R.layout.fragment_full_content, container, false);
        fullContentListView = (ListView)rootView.findViewById(R.id.fullContentListView);
        FullContentAdapter adapter = new FullContentAdapter(getActivity(), R.layout.pic_and_text_list, items );
        fullContentListView.setAdapter(adapter);

        showWebView();
        return rootView;
    }

    public  void showWebView() {
        View.OnClickListener textListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewFragment.class);
                startActivity(intent);

            }
        };
    }
}







package com.example.homework2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class News extends Fragment {

    private ListView lst;
    private String detail_text="";
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private Elements links;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        lst = view.findViewById(R.id.lst);

        new JSOUP().execute();


        return view;
    }

    public class JSOUP extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("loading...");
            dialog.show();

        }

        @Override
        protected Void doInBackground(Void... params){
            try{


                detail_text = "";
                arrayList = new ArrayList<>();
                Document doc =
                        Jsoup.connect("https://aybu.edu.tr/muhendislik/bilgisayar/").get();
                Elements elements = doc.select(".cnContent .cncItem");
                for(int i = 0; i<elements.size();i++){
                    detail_text = elements.get(i).text();
                    arrayList.add(detail_text);
                }
                links = doc.select(".cnContent .cncItem a");
            }

            catch (Exception e){

            }
            return null;
        }



        @Override
        protected void onPostExecute(Void result){
            dialog.dismiss();
            arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList.toArray(new String[arrayList.size()]));
            lst.setAdapter(arrayAdapter);


            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.aybu.edu.tr/muhendislik/bilgisayar/"+getElementLink(position)));
                    startActivity(browserIntent);
                }
            });


            super.onPostExecute(result);
        }

        private String getElementLink(int ind){
            return links.get(ind).attr("href");
        }



    }

}

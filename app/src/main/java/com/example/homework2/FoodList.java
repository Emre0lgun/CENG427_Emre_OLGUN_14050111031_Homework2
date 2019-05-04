package com.example.homework2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class FoodList extends Fragment {

    private TextView txt;
    private String detail_text="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        txt = view.findViewById(R.id.lst);

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
                Document doc =
                        Jsoup.connect("https://aybu.edu.tr/sks/").get();
                final Elements element = doc.select("table tbody tr td  table tbody tr");
                for(int i = 0; i<element.size();i++){
                    detail_text += "\n" + element.get(i).text();
                }
            }

            catch (Exception e){

            }
            return null;
        }



        @Override
        protected void onPostExecute(Void result){
            dialog.dismiss();
            txt.setText(detail_text);
            super.onPostExecute(result);
        }



    }

}

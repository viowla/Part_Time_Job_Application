package com.example.parttimejobapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FirstPageActivity extends AppCompatActivity {

    ListView listView;
    String[] vacancy_name={"nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs"};
    String[] vacancy_pay={"nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs"};
    String[] vacancy_comp={"nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs","nbhfbhs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        listView = findViewById(R.id.listView);
        MyAdapter myAdapter=new MyAdapter(this,vacancy_name,vacancy_pay,vacancy_comp);
        listView.setAdapter(myAdapter);
    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] vac_name;
        String[] vac_pay;
        String[] vac_comp;

        public MyAdapter(Context c, String[] vac_name, String[] vac_pay, String[] vac_comp){
            super(c, R.layout.vacancies_row,R.id.vacancy_name, vac_name);
            this.context=c;
            this.vac_name=vac_name;
            this.vac_pay=vac_pay;
            this.vac_comp=vac_comp;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater;
            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.vacancies_row,parent,false);
            TextView vac_name = row.findViewById(R.id.vacancy_name);
            TextView vac_pay = row.findViewById(R.id.vacancy_pay);
            TextView vac_comp = row.findViewById(R.id.vacancy_company);


            vac_name.setText(vacancy_name[position]);
            vac_pay.setText(vacancy_pay[position]);
            vac_comp.setText(vacancy_comp[position]);

            return row;
        }
    }
}
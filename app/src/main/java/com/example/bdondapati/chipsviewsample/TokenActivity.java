package com.example.bdondapati.chipsviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tokenautocomplete.FilteredArrayAdapter;
import com.tokenautocomplete.TokenCompleteTextView;

public class TokenActivity extends AppCompatActivity implements TokenCompleteTextView.TokenListener<Person> {
    ContactsCompletionView completionView;
    Person[] people;
    ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        people = new Person[]{
                new Person("Arun Kumar Chiluveru (PMT)", "achiluveru@phsi.us"),
                new Person("Bhaskar Dondapati (PMT)", "bdondapati@phsi.us"),
                new Person("Phanindra Surapaneni (PMT)", "psurapaneni@phsi.us"),
                new Person("Naveen Kommuri (PMT)", "nkommuri@phsi.us"),
                new Person("Kanthy Prasad Kalepu (PMT)", "kkalepu@phsi.us"),
                new Person("Uma Mahesh (PHS)", "umahesh@primehealthcare.com\t"),
                new Person("Joseph Daniel (PHS)", "jdaniel@primehealthcare.com"),
                new Person("Kondalrao Tirumalsetty (PMT)", "ktirumalsetty@phsi.us"),
                new Person("Lakshmana Chandu (SOH)", "lchandu@primehealthcare.com"),
                new Person("Praveen Kumar Konduru (PMT)", "pkonduru@primehealthcare.com"),
                new Person("Hima Majjiga (DMC)", "hmajjiga3@primehealthcare.com")
        };

        adapter = new FilteredArrayAdapter<Person>(this, R.layout.person_layout, people) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {

                    LayoutInflater l = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    convertView = l.inflate(R.layout.person_layout, parent, false);
                }

                Person p = getItem(position);
                ((TextView)convertView.findViewById(R.id.name)).setText(p.getName());
                ((TextView)convertView.findViewById(R.id.email)).setText(p.getEmail());

                return convertView;
            }

            @Override
            protected boolean keepObject(Person person, String mask) {
                mask = mask.toLowerCase();
                return person.getName().toLowerCase().startsWith(mask) || person.getEmail().toLowerCase().startsWith(mask);
               // return person.getName().toLowerCase().contains(mask) || person.getEmail().toLowerCase().contains(mask);
            }
        };

        completionView = (ContactsCompletionView)findViewById(R.id.searchView);
        completionView.setAdapter(adapter);
        completionView.setTokenListener(this);
        completionView.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select);

        if (savedInstanceState == null) {
            //completionView.setPrefix("To: ", Color.parseColor("blue"));
            completionView.setPrefix("");
           // completionView.addObject(people[0]);
            //completionView.addObject(people[1]);
        }
    }

    private void updateTokenConfirmation() {

        StringBuilder sb = new StringBuilder("Added To List :\n \n");
        for (Person token: completionView.getObjects()) {
            sb.append(token.getEmail());
            sb.append("\n");
        }
        ((TextView)findViewById(R.id.tokens)).setText(sb);
    }
    @Override
    public void onTokenAdded(Person token) {
        updateTokenConfirmation();
    }

    @Override
    public void onTokenRemoved(Person token) {
        updateTokenConfirmation();
    }
}

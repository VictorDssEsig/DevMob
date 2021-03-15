package com.example.calendrieteo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


// API : https://openweathermap.org/
// MA CLE : edc3201110804e78ff8b5c5e51491d62

// Exemple de requete : http://api.openweathermap.org/data/2.5/weather?q=Geneve,ch&APPID=edc3201110804e78ff8b5c5e51491d62

// EXEMPLE DE SORTIE DE L'API :
// ------------------------------------------------------------------------------------------------------------------------------------
// {"coord":{"lon":6.1457,"lat":46.2022},
// "weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],// "base":"stations",
// "main":{"temp":278.63,"feels_like":275.24,"temp_min":277.04,"temp_max":279.26,"pressure":1017,"humidity":81},
// "visibility":10000,"wind":{"speed":2.57,"deg":220},"clouds":{"all":75},"dt":1615822577,
// "sys":{"type":1,"id":6928,"country":"CH","sunrise":1615787291,"sunset":1615830034},
// "timezone":3600,"id":2660646,"name":"Geneva","cod":200}
// ------------------------------------------------------------------------------------------------------------------------------------


// LES DONNEES DE TEMPERATURE SONT EN KELVIN
// LE CALCUL POUR TRANSFORMER LES KELVIN EN DEGRES CELCIUS : 278 K − 273,15 = 5,48 °C
// 273.15 ETANT UNE CONSTANTE QUI NE CHANGE JAMAIS, LE 278K EST LE DEGRES VARIABLE

// TUTO ECRIT : https://medium.com/@sasude9/basic-android-weather-app-6a7c0855caf4
// VIDEO : https://www.youtube.com/watch?v=awYSrhUZQL0

// FIREBASE GITHUB : https://github.com/search?l=Java&q=android+firebase+crud&type=Repostories

// GEOLOCALISATION : POUR LES REQUETES DE L'API METEO FAIRE EN SORTE QUE CA S'ADAPTE AU LIEU OÙ NOUS NOUS TROUVONS
// VIDEO POUR GEOLOCALISATION https://www.youtube.com/watch?v=l-J6gDYtgFU



public class MainActivity extends AppCompatActivity {

    // https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android

    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    String formattedDate = df.format(c);

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    CalendarView calendarView;
    TextView myDate;
    Button continues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        myDate = findViewById(R.id.mydate);
        continues = findViewById(R.id.continues);
        continues.setEnabled(false);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                myDate.setText(date);
                SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
                Date dateObj = null;
                try {
                    dateObj = curFormater.parse((dayOfMonth+1) + "/" + (month+1) + "/" + year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (System.currentTimeMillis() <= dateObj.getTime()) {
                    continues.setEnabled(true);
                }else
                {
                    continues.setEnabled(false);
                }
            }
        });
        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, oneDayFocus.class);
                System.out.println("salsfrusadflkjasdfclalkuc");
                String message = myDate.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

    }
}
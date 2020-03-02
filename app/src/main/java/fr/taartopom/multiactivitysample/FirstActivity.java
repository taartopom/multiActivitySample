package fr.taartopom.multiactivitysample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    private final static int SECOND_CALL_ID = 1234;

    private EditText txtInputdata;
    private Button btnOpenActivity;
    private TextView lblResultText;

    private Button btnGoHome;
    private Button btnBrowse;
    private Button btnCaldulator1;
    private Button btncalculator2;
    private Button btnCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtInputdata = (EditText) findViewById(R.id.tv_input_data);
        btnOpenActivity = (Button) findViewById(R.id.btn_open_activity);
        lblResultText = (TextView) findViewById(R.id.lbl_result);

        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("debug", "bouton ouvrir second activity cliqué");
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("message", txtInputdata.getText().toString());
                /*FirstActivity.this*/
                startActivityForResult(intent, SECOND_CALL_ID);
                Log.i("debug", "ouvrir la second activity");
            }
        });

        //------- activités externes à l'application -------//

        /**
         * Ici on utilise un bouton pour retourné à la page d'acceuil de notre device
         */

        btnGoHome = (Button) findViewById(R.id.btn_go_home);
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("debug", "bouton home cliqué");
                Intent intentGoHome = new Intent(Intent.ACTION_MAIN);
                intentGoHome.addCategory(Intent.CATEGORY_HOME);
                startActivity(intentGoHome);
                Log.i("debug", "retour home ok");
            }
        });

        /**
         *Ici on utilise un bouton pour acceder au(x) navigateur(s) de notre device
         */
        btnBrowse = (Button) findViewById(R.id.btn_go_browse);
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* pour ouvrir un navigateur ou le navigateur par défaut*/

                //Log.i("debug", "bouton browse cliqué");
                //Intent intentGoBrowse =  new Intent( Intent.ACTION_MAIN);
                //intentGoBrowse.addCategory(Intent.CATEGORY_APP_BROWSER);
                //startActivity(intentGoBrowse);
                //Log.i("debug", "galerie ouverte ok");

                /*Pour ouvrir un page specifique avec son url*/

                Uri uri = Uri.parse("https://www.ecosia.org/images?q=yes#id=120BBF8E47D32E26BDED6DBF8B6A2EC81E855952 ");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        /**
         *Ici pour ouvrir la calculatrice de votre device ( attention cette méthode ne fonctionne pas sur un MV et sur certain smartphone)
         */
        btnCaldulator1 = (Button) findViewById(R.id.btn_calculator_one);
        btnCaldulator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalculator = new Intent(Intent.ACTION_MAIN);
                intentCalculator.addCategory(Intent.CATEGORY_APP_CALCULATOR);
                startActivity(intentCalculator);
            }
        });

        /**
         * Autre calculatrice qui fonctionne pour tous les supports
         */
        btncalculator2 = (Button) findViewById(R.id.btn_calculator_two);
        btncalculator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalculator = new Intent(Intent.ACTION_MAIN);
                intentCalculator.addCategory(Intent.CATEGORY_LAUNCHER);
                intentCalculator.setComponent(new ComponentName("com.android.calculator2", "com.android.calculator2.Calculator"));
                startActivity(intentCalculator);
            }
        });

        /**
         * Pour lançer un appel téléphonique ( ajouter les autorisations dans manifeste.xml)
         */
        btnCall = (Button) findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel: 123456789");
                Intent intentCall = new Intent(Intent.ACTION_CALL, uri);
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String [] permissions = {
                            Manifest.permission.CALL_PHONE
                    };
                    requestPermissions( permissions, 1000);
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(intentCall);
            }
        });

    }

    /**
     * Pour reccuperer les informations entre les activités
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == SECOND_CALL_ID){
            lblResultText.setText("Result == " + resultCode);
        }
    }
}

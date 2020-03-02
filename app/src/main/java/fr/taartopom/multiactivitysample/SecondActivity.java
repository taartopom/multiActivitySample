package fr.taartopom.multiactivitysample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView lblInputData;
    private Button btnCloseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.i("debug", "direction vers second activity ok");

        lblInputData = (TextView) findViewById(R.id.lbl_input_data);
        btnCloseActivity =  (Button) findViewById(R.id.btn_close);

        //pour la réccuperation de l'input de FirstActivity vers SecondActivité
        String inputData = this.getIntent().getExtras().getString("message");
        lblInputData.setText(inputData);

        Log.i("debug", "récupération input ok");
        btnCloseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult( 33 );
                finish();
            }
        });

    }
}

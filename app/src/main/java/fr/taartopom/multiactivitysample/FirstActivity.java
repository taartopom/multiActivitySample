package fr.taartopom.multiactivitysample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                Intent intent =  new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("message", txtInputdata.getText().toString() );
                /*FirstActivity.this*/ startActivityForResult( intent, SECOND_CALL_ID);
                Log.i("debug", "ouvrir la second activity");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == SECOND_CALL_ID){
            lblResultText.setText("Result == " + resultCode);
        }
    }
}

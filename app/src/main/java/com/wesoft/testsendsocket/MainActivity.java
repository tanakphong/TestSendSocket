package com.wesoft.testsendsocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.wesoft.testsendsocket.Class.SimpleTCPClient;


public class MainActivity extends AppCompatActivity {

    private EditText IPDest;
    private EditText Port;
    private EditText Line1;
    private EditText Line2;
    private EditText Line3;
    private EditText Line4;
    private EditText Line5;
    private EditText Line6;
    private EditText Line71;
    private EditText Line72;
    private EditText Line81;
    private EditText Line82;
    private AppCompatButton Send;
    private Spinner spnDelimiter;
    private EditText mTxtDelimiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IPDest = (EditText) findViewById(R.id.txtIPDestination);
        Port = (EditText) findViewById(R.id.txtPort);
        Line1 = (EditText) findViewById(R.id.txtLine1);
        Line2 = (EditText) findViewById(R.id.txtLine2);
        Line3 = (EditText) findViewById(R.id.txtLine3);
        Line4 = (EditText) findViewById(R.id.txtLine4);
        Line5 = (EditText) findViewById(R.id.txtLine5);
        Line6 = (EditText) findViewById(R.id.txtLine6);
        Line71 = (EditText) findViewById(R.id.txtLine71);
        Line72 = (EditText) findViewById(R.id.txtLine72);
        Line81 = (EditText) findViewById(R.id.txtLine81);
        Line82 = (EditText) findViewById(R.id.txtLine82);
        Send = (AppCompatButton) findViewById(R.id.btnSend);



        spnDelimiter = (Spinner) findViewById(R.id.delimiter_spinner);
        ArrayAdapter<CharSequence> adpDelimiter= ArrayAdapter.createFromResource(this,
                R.array.delimiter_array, android.R.layout.simple_spinner_item);
        adpDelimiter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mTxtDelimiter = (EditText) findViewById(R.id.txtDelimiter);
        mTxtDelimiter.setVisibility(View.INVISIBLE);

        spnDelimiter.setAdapter(adpDelimiter);

        spnDelimiter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                boolean bo = false;
//                switch (parent.getItemAtPosition(position).toString()) {
//                    case "Comma":
//                        delimiter = ",";
//                        break;
//                    case "Tab":
//                        delimiter = "\t";
//                        break;
//                    case "SemiColon":
//                        delimiter = ";";
//                        break;
//                    case "Space":
//                        delimiter = " ";
//                        break;
//                    case "Pipe":
//                        delimiter = "\\|";
//                        break;
//                    case "Manual":
//                        bo = true;
//                        break;
//                    default:
//                        delimiter = "";
//                }
                if (parent.getItemAtPosition(position).toString().equals("Manual")) {
                    mTxtDelimiter.setVisibility(View.VISIBLE);
                } else {
                    mTxtDelimiter.setVisibility(View.INVISIBLE);
                }
                mTxtDelimiter.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipd = IPDest.getText().toString();
                Integer port = Integer.valueOf(Port.getText().toString());
                String delimiter = getDelimiter(spnDelimiter.getSelectedItem().toString());
                String message = Line1.getText().toString() + delimiter +
                        Line2.getText().toString() + delimiter +
                        Line3.getText().toString() + delimiter +
                        Line4.getText().toString() + delimiter +
                        Line5.getText().toString() + delimiter +
                        Line6.getText().toString() + delimiter +
                        Line71.getText().toString() + delimiter +
                        Line72.getText().toString() + delimiter +
                        Line81.getText().toString() + delimiter +
                        Line82.getText().toString();
                Log.i("dlg", "ipd: " + ipd + ":" + port);
                Log.i("dlg", "message: " + message);
                SimpleTCPClient.send(message, ipd, port);
                Log.i("dlg", "SimpleTCPClient.send: OK");
            }
        });
    }

    private String getDelimiter(String value) {
        String ret;
        switch (value.toUpperCase()) {
            case "COMMA":
                ret = ",";
                break;
            case "TAB":
                ret = "\t";
                break;
            case "SEMICOLON":
                ret = ";";
                break;
            case "SPACE":
                ret = " ";
                break;
            case "PIPE":
                ret = "|";
                break;
            case "MANUAL":
                ret = mTxtDelimiter.getText().toString();
                break;
            default:
                ret="";
        }
        return ret;
    }
}

package com.example.blokzakartanje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class FivePlayerActivity extends AppCompatActivity {
    int[] bod = new int[]{0,0,0,0,0};
    int[] bod_undo = new int[]{0,0,0,0,0};
    TextView[] tvBod=new TextView[5];
    TextView[] tvIme=new TextView[5];
    int mijesa=0;
    String[] ime=new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_player);
        tvBod[0]=findViewById(R.id.pettvbodoviprvog);
        tvBod[1]=findViewById(R.id.pettvbodovidrugog);
        tvBod[2]=findViewById(R.id.pettvbodovitreceg);
        tvBod[3]=findViewById(R.id.pettvbodovicetvrtog);
        tvBod[4]=findViewById(R.id.pettvbodovipetog);

        tvIme[0]=findViewById(R.id.pettvimeprvog);
        tvIme[1]=findViewById(R.id.pettvimedrugog);
        tvIme[2]=findViewById(R.id.pettvimetreceg);
        tvIme[3]=findViewById(R.id.pettvimecetvrtog);
        tvIme[4]=findViewById(R.id.pettvimepetog);


        unosImena();
        updateMjesa();

    }


    public void undoBodovi(View v){
        for(int i=0;i<5;i++){
            bod[i]=bod_undo[i];
        }
        mijesa--;
        if(mijesa==-1)mijesa=4;
        updateMjesa();
        updateBodovi();
    }

    public void unosBodova(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar);
        LayoutInflater inflater = FivePlayerActivity.this.getLayoutInflater();
        builder.setView(R.layout.dialog5bodovi);
        //In case it gives you an error for setView(View) try
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog5bodovi, null);
        builder.setView(customLayout);
        final AlertDialog show=builder.show();

        EditText[] etBod=new EditText[5];
        etBod[0]=customLayout.findViewById(R.id.editText);
        etBod[1]=customLayout.findViewById(R.id.editText2);
        etBod[2]=customLayout.findViewById(R.id.editText3);
        etBod[3]=customLayout.findViewById(R.id.editText4);
        etBod[4]=customLayout.findViewById(R.id.editText5);
        for(int i=0;i<5;i++){
            etBod[i].setHint(ime[i]);
        }
        final EditText focus=customLayout.findViewById(R.id.editText);

        focus.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        final EditText FinalFocus=customLayout.findViewById(R.id.editText5);
        FinalFocus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) FivePlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                    customLayout.findViewById(R.id.btn).callOnClick();
                }
                return false;
            }
        });
        customLayout.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mijesa++;
                if(mijesa==5)mijesa=0;
                updateMjesa();
                EditText[] etBod=new EditText[5];
                etBod[0]=customLayout.findViewById(R.id.editText);
                etBod[1]=customLayout.findViewById(R.id.editText2);
                etBod[2]=customLayout.findViewById(R.id.editText3);
                etBod[3]=customLayout.findViewById(R.id.editText4);
                etBod[4]=customLayout.findViewById(R.id.editText5);
                String[] ime=new String[5];
                for(int i=0;i<5;i++){
                    bod_undo[i]=bod[i];
                    if(!etBod[i].getText().toString().equals("")){
                        bod[i]+=Integer.valueOf(etBod[i].getText().toString());
                    }
                }
                updateBodovi();
                InputMethodManager imm = (InputMethodManager) FivePlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                show.dismiss();
            }
        });

    }


    void updateMjesa(){
            for(int i=0;i<5;i++){
                tvIme[i].setTextColor(Color.WHITE);
            }
            tvIme[mijesa].setTextColor(Color.MAGENTA);
    }



    void unosImena(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar);
        LayoutInflater inflater = FivePlayerActivity.this.getLayoutInflater();
        builder.setCancelable(false);
        builder.setView(R.layout.dialogpetimena);
        //In case it gives you an error for setView(View) try
        final View customLayout = getLayoutInflater().inflate(R.layout.dialogpetimena, null);
        builder.setView(customLayout);
        final AlertDialog show=builder.show();
        final EditText focus=customLayout.findViewById(R.id.editText);
        focus.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        final EditText FinalFocus=customLayout.findViewById(R.id.editText5);
        FinalFocus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    InputMethodManager imm = (InputMethodManager) FivePlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                    customLayout.findViewById(R.id.btn).callOnClick();
                }
                return false;
            }
        });
        customLayout.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[] etIme=new EditText[5];
                etIme[0]=customLayout.findViewById(R.id.editText);
                etIme[1]=customLayout.findViewById(R.id.editText2);
                etIme[2]=customLayout.findViewById(R.id.editText3);
                etIme[3]=customLayout.findViewById(R.id.editText4);
                etIme[4]=customLayout.findViewById(R.id.editText5);

                for(int i=0;i<5;i++){
                    if(!etIme[i].getText().toString().equals("")){
                        ime[i]=etIme[i].getText().toString();
                    }
                    else ime[i]="Nema imena";
                }
                for(int i=0;i<5;i++){
                    tvIme[i].setText(ime[i]);
                }
                InputMethodManager imm = (InputMethodManager) FivePlayerActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FinalFocus.getWindowToken(), 0);
                show.dismiss();
            }
        });
    }

    void updateBodovi(){
        for(int i=0;i<5;i++){
            tvBod[i].setText(String.valueOf(bod[i]));
        }
    }
}

package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static java.security.AccessController.getContext;

public class SelectCityActivity extends AppCompatActivity {

    final String MYLOG = "metamorphosisOfActivity";
    public View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_select_city);
        findViewById(R.id.btnTown1).setOnClickListener( btnClick );
        findViewById(R.id.btnTown2).setOnClickListener( btnClick );
        findViewById(R.id.btnTown3).setOnClickListener( btnClick );


        if(savedInstanceState == null){
            Log.d(MYLOG, "onCreate() первый раз");
        }else{
            Log.d(MYLOG, "onCreate() повторно");
        }
    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MYLOG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MYLOG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MYLOG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MYLOG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MYLOG, "onDestroy()");
    }


        private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Toast.makeText(SelectCityActivity.this, ((Button)view).getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("name", ((Button)view).getText().toString());

            switch (view.getId()){
                case R.id.btnTown1:
                    intent.putExtra("nameImage", R.drawable.moscow);
                    break;
                case R.id.btnTown2:
                    intent.putExtra("nameImage", R.drawable.madrid);
                    break;
                case R.id.btnTown3:
                    intent.putExtra("nameImage", R.drawable.london);
                    break;
            }


            setResult(RESULT_OK, intent);
            finish();
        }
    };

}
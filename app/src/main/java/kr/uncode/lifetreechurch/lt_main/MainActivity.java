package kr.uncode.lifetreechurch.lt_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.uncode.lifetreechurch.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setTollbarTitle(String title) {
    }
}

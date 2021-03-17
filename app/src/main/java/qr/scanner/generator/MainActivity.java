package qr.scanner.generator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIMER = 5000;

    // 3 Seconds = 3000 Milliseconds
    //5 Seconds = 5000 Milliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if (isFirstTime) {
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();
                    Intent onBoardingIntent = new Intent(MainActivity.this, PaperOnboardingActivity.class);
                    startActivity(onBoardingIntent);
                } else {
                    Intent intent = new Intent(MainActivity.this, QRCodeScanner.class);
                    startActivity(intent);
                }
                finish();
            }
        }, SPLASH_TIMER);
    }
}
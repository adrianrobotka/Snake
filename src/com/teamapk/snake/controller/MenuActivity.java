package com.teamapk.snake.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.beesmarter.teamapk.snake.R;
import com.teamapk.snake.Config;

public final class MenuActivity extends Activity {
    private AppController controller = AppController.getInstance();
    private Button newGameButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        controller.setFps(Config.FPS);

        newGameButton = (Button) findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        Button howToPlayButton = (Button) findViewById(R.id.howToPlayButton);
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HowToPlayActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (controller.isGameEnded()) {
            newGameButton.setText(R.string.newGameButton);
            controller.init();
        } else {
            newGameButton.setText(R.string.continueGameButton);
        }
    }
}

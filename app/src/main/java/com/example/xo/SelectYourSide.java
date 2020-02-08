package com.example.xo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.xo.databinding.ActivitySelectYourSideBinding;

public class SelectYourSide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivitySelectYourSideBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_select_your_side);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent in = new Intent(SelectYourSide.this, PlayWithFriend.class);
                startActivity(in);*/

                String oNamePlayer, xNamePlayer;
                oNamePlayer = binding.etOPlayer.getText().toString().trim();
                xNamePlayer = binding.etXPlayer.getText().toString().trim();
                if (oNamePlayer.isEmpty()) {
                    binding.etOPlayer.setError("" + R.string.please_enter_name_of_o_player);
                } else if (xNamePlayer.isEmpty()) {
                    binding.etXPlayer.setError(""+ R.string.please_enter_name_of_x_player);
                } else {
                    Intent in = new Intent(SelectYourSide.this, PlayWithFriend.class);
                    in.putExtra("oNamePlayer", oNamePlayer);
                    in.putExtra("xNamePlayer", xNamePlayer);
                    startActivity(in);
                    finish();
                }
            }
        });
    }
}

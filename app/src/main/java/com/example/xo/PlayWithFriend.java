package com.example.xo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xo.databinding.ActivityPlayWithFriendBinding;

public class PlayWithFriend extends AppCompatActivity {

    Intent intent;
    String oNamePlayer, xNamePlayer;
    // 0 for O and 1 for x
    int activePlayer = 0;
    int oWins = 0;
    int xWins = 0;
    int draws = 0;
    ActivityPlayWithFriendBinding binding;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int gameMoves = 0;
    boolean gameActive = true;
    int[][] winingPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_with_friend);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();
        oNamePlayer = intent.getStringExtra("oNamePlayer");
        xNamePlayer = intent.getStringExtra("xNamePlayer");
        binding.itsTurnOf.setText("it's turn of: " + oNamePlayer);
        binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.blue));

        binding.btnCloseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayWithFriend.this, R.style.AlertDialogStyle);
                builder.setMessage("Do you want to close this game ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent in = new Intent(PlayWithFriend.this, MainActivity.class);
                                startActivity(in);
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog CloseGame = builder.create();
                CloseGame.setTitle("Alert To Exit!!");
                CloseGame.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PlayWithFriend.this, R.style.AlertDialogStyle);
        builder.setMessage("Do you want to close this game ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(PlayWithFriend.this, MainActivity.class);
                        startActivity(in);
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog CloseGame = builder.create();
        CloseGame.setTitle("Alert To Exit!!");
        CloseGame.show();

    }

    public void dropin(View view) {

        gameMoves++;
        ImageButton counter = (ImageButton) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                binding.itsTurnOf.setText("it's turn of: " + xNamePlayer);
                binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.red));
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                binding.itsTurnOf.setText("it's turn of: " + oNamePlayer);
                binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.blue));
                activePlayer = 0;
            }
            counter.animate().setDuration(300);
            //that line to make imageButton have just one click
            //counter.setEnabled(false);

            for (int[] winingPosition : winingPosition) {
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]]
                        && gameState[winingPosition[1]] == gameState[winingPosition[2]] && gameState[winingPosition[0]] != 2) {

                    gameMoves = 0;
                    gameActive = false;
                    String winner;
                    if (activePlayer == 1) {
                        winner = oNamePlayer;
                        oWins = oWins + 1;
                        binding.oWins.setText(oWins + " wins");
                        binding.itsTurnOf.setText(winner + " has won!");
                        binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.blue));
                    } else if (activePlayer == 0) {
                        winner = xNamePlayer;
                        xWins = xWins + 1;
                        binding.xWins.setText(xWins + " wins");
                        binding.itsTurnOf.setText(winner + " has won!");
                        binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.red));
                    }
                    binding.parentBtnPlayAgain.setVisibility(View.VISIBLE);
                    binding.tvPlayAgain.setVisibility(View.VISIBLE);
                } else if (gameState[winingPosition[0]] != gameState[winingPosition[1]]
                        && gameState[winingPosition[1]] != gameState[winingPosition[2]]
                        && gameState[winingPosition[0]] != 2 && gameMoves >= 9) {

                    gameMoves = 0;
                    gameActive = false;
                    draws = draws + 1;
                    binding.draws.setText(draws + " draws");
                    binding.itsTurnOf.setText("there is no one winner!");
                    binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.gray));
                    binding.parentBtnPlayAgain.setVisibility(View.VISIBLE);
                    binding.tvPlayAgain.setVisibility(View.VISIBLE);

                }

            }


        }
    }

    public void playAgain(View view) {

        binding.parentBtnPlayAgain.setVisibility(View.INVISIBLE);
        binding.tvPlayAgain.setVisibility(View.INVISIBLE);

        binding.itsTurnOf.setText("it's turn of: " + oNamePlayer);
        binding.itsTurnOf.setTextColor(this.getResources().getColor(R.color.blue));

        for (int i = 0; i < binding.line1.getChildCount(); i++) {

            ImageButton counter = (ImageButton) binding.line1.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < binding.line2.getChildCount(); i++) {

            ImageButton counter = (ImageButton) binding.line2.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < binding.line3.getChildCount(); i++) {

            ImageButton counter = (ImageButton) binding.line3.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
        gameMoves = 0;
    }
}

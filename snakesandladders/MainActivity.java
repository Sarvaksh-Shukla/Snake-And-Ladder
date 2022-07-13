package com.example.snakesandladders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static Button rollbutton;public static Button rollbutton2;
    public static TextView textView;
    public static ImageView img1, img2;
    Dice dice;
    int roll, position;
    ImageButton goti[][] = new ImageButton[6][6];
    List<ImageButton> positions = new ArrayList<ImageButton>();
    public boolean player1turn = true;
    public boolean player2turn = true;
    public boolean gamestart ;
    int num;
    final int grid[][] = {{R.id.position0, R.id.position1, R.id.position2, R.id.position3, R.id.position4, R.id.position5},
            {R.id.position6, R.id.position7, R.id.position8, R.id.position9, R.id.position10, R.id.position11},
            {R.id.position12, R.id.position13, R.id.position14, R.id.position15, R.id.position16, R.id.position17},
            {R.id.position18, R.id.position19, R.id.position20, R.id.position21, R.id.position22, R.id.position23},
            {R.id.position24, R.id.position25, R.id.position26, R.id.position27, R.id.position28, R.id.position29},
            {R.id.position30, R.id.position31, R.id.position32, R.id.position33, R.id.position34, R.id.position35},};
    int play1pos=0, play2pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice = new Dice();
        rollbutton = findViewById(R.id.button);
        rollbutton2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView2);
        img1 = findViewById(R.id.imageView38);
        img2 = findViewById(R.id.imageView39);
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++)
                goti[y][x] = (ImageButton) findViewById(grid[y][x]);
        rollbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll = dice.getNum();
                textView.setText(""+roll);


                if (player2turn) {
                    gamestart = false;

                    if (play2pos == 0) {
                        if (roll == 6) {
                            gotimove(gamestart,1, play2pos);
                            player1turn = false;
                        } else {
                            player2turn = false;
                            player1turn = true;
                        }

                    } else if (play2pos > 0 ) {
                        if (play2pos + roll == 36) {
                            gameover(false);
                            gotimove(gamestart, roll, play2pos);
                        } else if (play2pos + roll < 36) {
                            gotimove(gamestart, roll, play2pos);
                            player1turn = true;
                            player2turn = false;
                            if (roll == 6) {
                                player1turn = false;
                                player2turn = true;
                            }
                        } else if (play1pos + roll > 36) {
                            player1turn = false;
                            player2turn = true;
                        }
                    }
                }
            }

        });
        rollbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll = dice.getNum();textView.setText(""+roll);


                if (player1turn) {
                    gamestart = true;

                    if (play1pos == 0) {
                        if (roll == 6) {
                            gotimove(gamestart, 1, play1pos);
                            player2turn = false;
                        } else {
                            player1turn = false;
                            player2turn = true;
                        }

                    } else if (play1pos > 0 ) {
                        if (play1pos + roll == 36) {
                            gameover(true);
                            gotimove(gamestart, roll, play1pos);
                        } else if (play1pos + roll < 36) {
                            gotimove(gamestart, roll, play1pos);
                            player2turn = true;
                            player1turn = false;
                            if (roll == 6) {
                                player2turn = false;
                                player1turn = true;
                            }
                        } else if (play1pos + roll > 36) {
                            player2turn = false;
                            player1turn = true;
                        }
                    }
                }
            }

        });
    }
    void gotimove(boolean chalo,int size,int initialposition){
        int newposition=initialposition+size;
        char poschar[]=new char[2];
        char dubaraposchar[]=new char[2];
        poschar=String.valueOf(newposition-1).toCharArray();
        dubaraposchar=String.valueOf(initialposition-1).toCharArray();
        int px=0,rpx=0;
        int py=0,rpy=0;
        if(newposition-1<6){
            px=newposition-1;
            py=0;
        }else{
            px=Integer.parseInt(poschar[1]+"");
            py=Integer.parseInt(poschar[0]+"");
        }
        if(initialposition-1<0){
            rpx=0;
            rpy=0;
        }
        else if(initialposition-1<6){
            rpy=0;
            rpx=initialposition-1;
        }else {
            rpy=Integer.parseInt(dubaraposchar[0]+"");
            rpx=Integer.parseInt(dubaraposchar[1]+"");
        }
        if(chalo&&newposition<36){
            goti[rpy][rpx].setBackground(getResources().getDrawable(R.color.transparent));
            goti[py][px].setBackground(getResources().getDrawable(R.drawable.play1));
            play1pos=newposition;
        }
        else if(chalo&&newposition<36) {
            goti[rpy][rpx].setBackground(getResources().getDrawable(R.color.transparent));
            goti[py][px].setBackground(getResources().getDrawable(R.drawable.play2));
            play2pos = newposition;
        }else if(newposition==36){
            if(chalo){
                goti[5][5].setBackground(getResources().getDrawable(R.drawable.play1));
            }else{
                goti[5][5].setBackground(getResources().getDrawable(R.drawable.play2));
            }
        }
    }
    void gameover(boolean start){
        if(gamestart){
            textView.setText("player1 won");
        }else {
            textView.setText("player 2 won");
        }
        player1turn=false;player2turn=false;

    }
}

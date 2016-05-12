package com.example.mahmoud.conect3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int activeplayer = 0;
    private int[] avalableplaces ={2,2,2,2,2,2,2,2,2};
    private int [][] winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private TextView TV ;
    private boolean gameRunning = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TV=(TextView)findViewById(R.id.winning);
        TV.setVisibility(View.INVISIBLE);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void dropIn(View  v){
        ImageView counter = (ImageView) v;

        int tapedCounter = Integer.parseInt(counter.getTag().toString());
        if(avalableplaces[tapedCounter]== 2 && gameRunning == true) {
            avalableplaces[tapedCounter]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }

            counter.animate().translationYBy(1000f).rotationYBy(360f).setDuration(400);

            for(int [] winningposition : winning){
                if(avalableplaces[winningposition[0]] == avalableplaces[winningposition[1]] &&
                        avalableplaces[winningposition[1]] == avalableplaces[winningposition[2]]&&
                                avalableplaces[winningposition[0]] != 2){
                    String winner =  "";
                    gameRunning = false;
                    if(0 == avalableplaces[winningposition[0]]){
                        winner="yellow";
                    }
                    else{
                        winner="red";
                    }
                    final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.winningLayout);
                    TextView msg = (TextView)findViewById(R.id.winnermsg);
                    msg.setText(winner + " WINS...!");
                    Button btn = (Button)findViewById(R.id.playAgainbutton);
                    linearLayout.setVisibility(View.VISIBLE);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int [] aavalableplaces ={2,2,2,2,2,2,2,2,2};
                            avalableplaces=aavalableplaces;

                            activeplayer = 0;
                            linearLayout.setVisibility(View.INVISIBLE);
                            GridLayout gridLayout = (GridLayout )findViewById(R.id.gridLayout);
                            for(int i = 0; i<gridLayout.getChildCount() ; i++){
                                ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
                            }
                            gameRunning=true;
                        }
                    });
                }else{
                    boolean gameisOver=true;
                    for(int x : avalableplaces){
                        if (x==2){
                            gameisOver=false;
                        }
                    }
                    if(gameisOver){
                        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.winningLayout);
                        TextView msg = (TextView)findViewById(R.id.winnermsg);
                        msg.setText("Draw");
                        msg.setAllCaps(true);
                        Button btn = (Button)findViewById(R.id.playAgainbutton);
                        linearLayout.setVisibility(View.VISIBLE);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int [] aavalableplaces ={2,2,2,2,2,2,2,2,2};
                                avalableplaces=aavalableplaces;
                                activeplayer = 0;
                                linearLayout.setVisibility(View.INVISIBLE);
                                GridLayout gridLayout = (GridLayout )findViewById(R.id.gridLayout);
                                for(int i = 0; i<gridLayout.getChildCount() ; i++){
                                    ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
                                }
                                gameRunning=true;
                            }
                        });
                    }
                }
            }
        }
    }









//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}

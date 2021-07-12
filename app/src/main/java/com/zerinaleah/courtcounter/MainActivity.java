package com.zerinaleah.courtcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import androidx.appcompat.widget.ShareActionProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int points=0;
    int pointsB=0;

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    if(savedInstanceState!=null) {
       points = savedInstanceState.getInt("points");
        pointsB =savedInstanceState.getInt("pointsB");
       /* TextView txtA = findViewById(R.id.ptsA);
        TextView txtB = findViewById(R.id.ptsB);
        txtA.setText(String.valueOf(points));
        txtB.setText(String.valueOf(pointsB));*/
       displayTeamA(points);
       displayTeamB(pointsB);
    }
    }

    public void displayTeamA (int number) {
        TextView pointsView = (TextView) findViewById(R.id.ptsA);
        pointsView.setText(String.valueOf(number));
    }
    public void threePtsA(View view) {
        points=points+3;
        displayTeamA(points);
    }
    public void twoPtsA(View view) {
        points=points+2;
        displayTeamA(points);
    }
    public void freeThrowA(View view) {
        points++;
        displayTeamA(points);
    }

    public void displayTeamB (int number) {
        TextView pointsViewB = (TextView) findViewById(R.id.ptsB);
        pointsViewB.setText(String.valueOf(number));
    }
    public void threePtsB(View view) {
        pointsB+=3;
        displayTeamB(pointsB);
    }
    public void twoPtsB(View view) {
        pointsB+=2;
        displayTeamB(pointsB);
    }
    public void freeThrowB(View view) {
        pointsB++;
        displayTeamB(pointsB);
    }

    public String winnerTeam(){
        String winner;
        if(points>pointsB){
            winner ="Team A has won!";
        } else
        {
            winner ="Team B has won!";
        }
        return winner;
    }
    public  void reset(View view){
        points=0;
        pointsB=0;
        displayTeamA(points);
        displayTeamB(pointsB);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("points", points);
        savedInstanceState.putInt("pointsB", pointsB);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.share_action);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent(winnerTeam());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void setShareActionIntent(String text) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_TEXT, text);
    intent.setType("text/plain");
    shareActionProvider.setShareIntent(intent);
    }

}
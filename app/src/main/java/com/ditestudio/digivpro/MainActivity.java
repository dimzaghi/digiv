package com.ditestudio.digivpro;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton quitButton = (ImageButton) findViewById(R.id.quitApp);
        quitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showExitConfirmDialog();
            }
        });

        ImageButton whoButton = (ImageButton) findViewById(R.id.whoWeAreButton);

        whoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(MainActivity.this,WhoWeAre.class);
                startActivity(newIntent);
            }
        });

        ImageButton ourButton = (ImageButton) findViewById(R.id.ourEventsButton);

        ourButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent1 = new Intent(MainActivity.this,ourEvents.class);
                startActivity(newIntent1);
            }
        });

        ImageButton storyButton = (ImageButton) findViewById(R.id.ourStoryButton);

        storyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent2 = new Intent(MainActivity.this,ourStory.class);
                startActivity(newIntent2);
            }
        });

        ImageButton arButton = (ImageButton) findViewById(R.id.arBoothButton);

        arButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentAR = new Intent(MainActivity.this,ARbooth.class);
                startActivity(newIntentAR);
            }
        });

//        ImageButton guestButton = (ImageButton) findViewById(R.id.guestBookButton);
//
//        guestButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent newIntent3 = new Intent(MainActivity.this,guestBook.class);
//                startActivity(newIntent3);
//            }
//        });

        ImageButton guestButton = (ImageButton) findViewById(R.id.guestBookButton);

        guestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });

        ImageButton mapsButton = (ImageButton) findViewById(R.id.mapsButton);

        mapsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent3 = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(newIntent3);
            }
        });


    }

    protected void sendEmail() {
        Log.i("Share Story", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:marfianiannisa@gmail.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Digital Invitation Story - Hendri & Anisa Wedding");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Digital Invitation Story - You're Invited to Our Happiness Events, Please Check This Out https://play.google.com/store/apps/details?id=com.ditestudio.digivpro");

        try {
            startActivity(Intent.createChooser(emailIntent, "Share Story..."));
            finish();
            Log.i("Finished share story...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            showExitConfirmDialog();

        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();

    }


    public void showExitConfirmDialog(){ // just show an dialog

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("DiGiV App"); // set title
        dialog.setIcon(R.mipmap.ic_launcher); // set icon
        dialog.setMessage("Are you sure want to exit?"); // set message
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        int pid = android.os.Process.myPid();
//                        android.os.Process.killProcess(pid);

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                        //MainActivity.this.finish(); // when click OK button, finish current activity!
                        finish();
                        System.exit(0);
                    }
                });
        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show(); // just show a Toast, do nothing else
                    }
                });
        dialog.create().show();
    }


}
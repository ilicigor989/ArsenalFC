package com.example.arsenalfc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final int PERMISSION_REQUEST = 1;
    File directory;
    File sdCard;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Player> myListPlayers;
    private static final int REQUEST_CODE = 1;
    int playerPosition;
    String myCurrentFilePath;
    ImageView rv_imageView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewMyLayout = inflater.inflate(R.layout.list_item, null);
        rv_imageView=viewMyLayout.findViewById(R.id.item_imageview);



        sdCard = Environment.getExternalStorageDirectory();
        directory = new File(sdCard.getAbsolutePath() + "/Arsenal FC");

        myListPlayers = new ArrayList<>();


        if (!directory.exists()) {
            String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, PERMISSION_REQUEST);

        } else {
            Player Ashley = new Player(getString(R.string.p1), getString(R.string.left_defender), getString(R.string.dp1), getPicturePath("Ashley Cole"));
            myListPlayers.add(Ashley);
            Player Adebayor = new Player(getString(R.string.p2), getString(R.string.striker), getString(R.string.dp2), getPicturePath("Emmanuel Adebayor"));
            myListPlayers.add(Adebayor);
            Player Sagna = new Player(getString(R.string.p3), getString(R.string.right_defender), getString(R.string.dp3), getPicturePath("Bacary Sagna"));
            myListPlayers.add(Sagna);
            Player Bendtner = new Player(getString(R.string.p4), getString(R.string.striker), getString(R.string.dp4), getPicturePath("Nicklas Bendtner"));
            myListPlayers.add(Bendtner);
            Player Seaman = new Player(getString(R.string.p5), getString(R.string.goal_keeper), getString(R.string.dp5), getPicturePath("David Seaman"));
            myListPlayers.add(Seaman);
            Player Bergkamp = new Player(getString(R.string.p6), getString(R.string.striker), getString(R.string.dp6), getPicturePath("Dennis Bergkamp"));
            myListPlayers.add(Bergkamp);
            Player Fabregas = new Player(getString(R.string.p7), getString(R.string.central_midfielder), getString(R.string.dp7), getPicturePath("Cesc Fabregas"));
            myListPlayers.add(Fabregas);
            Player Clichy = new Player(getString(R.string.p8), getString(R.string.left_defender), getString(R.string.dp8), getPicturePath("Gaël Clichy"));
            myListPlayers.add(Clichy);
            Player Xhaka = new Player(getString(R.string.p9), getString(R.string.central_midfielder), getString(R.string.dp9), getPicturePath("Granit Xhaka"));
            myListPlayers.add(Xhaka);
            Player Wilshere = new Player(getString(R.string.p10), getString(R.string.central_midfielder), getString(R.string.dp10), getPicturePath("Jack Wilshere"));
            myListPlayers.add(Wilshere);
            Player Lehmann = new Player(getString(R.string.p11), getString(R.string.goal_keeper), getString(R.string.dp11), getPicturePath("Jens Lehmann"));
            myListPlayers.add(Lehmann);
            Player Almunia = new Player(getString(R.string.p12), getString(R.string.goal_keeper), getString(R.string.dp12), getPicturePath("Manuel Almunia"));
            myListPlayers.add(Almunia);
            Player Monreal = new Player(getString(R.string.p13), getString(R.string.right_defender), getString(R.string.dp13), getPicturePath("Nacho Monreal"));
            myListPlayers.add(Monreal);
            Player Pires = new Player(getString(R.string.p14), getString(R.string.left_midfielder), getString(R.string.dp14), getPicturePath("Robert Pires"));
            myListPlayers.add(Pires);
            Player Campbell = new Player(getString(R.string.p15), getString(R.string.central_defender), getString(R.string.dp15), getPicturePath("Sol Campbell"));
            myListPlayers.add(Campbell);
            Player Henry = new Player(getString(R.string.p16), getString(R.string.left_forward), getString(R.string.dp16), getPicturePath("Thierry Henry"));
            myListPlayers.add(Henry);
            Player Rosicky = new Player(getString(R.string.p17), getString(R.string.right_midfielder), getString(R.string.dp17), getPicturePath("Tomas Rosicky"));
            myListPlayers.add(Rosicky);
            Player Adams = new Player(getString(R.string.p18), getString(R.string.central_defender), getString(R.string.dp18), getPicturePath("Tony Adams"));
            myListPlayers.add(Adams);
            Player Vieira = new Player(getString(R.string.p19), getString(R.string.central_midfielder), getString(R.string.dp19), getPicturePath("Patrick Vieira"));
            myListPlayers.add(Vieira);

            recyclerViewAdapter = new RecyclerViewAdapter(myListPlayers,getBaseContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.ashley_cole);
                saveToExternalMemory(b2, "Ashley Cole");
                Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.adebayor);
                saveToExternalMemory(b1, "Emmanuel Adebayor");
                Bitmap b3 = BitmapFactory.decodeResource(getResources(), R.drawable.bacary_sagna);
                saveToExternalMemory(b3, "Bacary Sagna");
                Bitmap b4 = BitmapFactory.decodeResource(getResources(), R.drawable.bendtner);
                saveToExternalMemory(b4, "Nicklas Bendtner");
                Bitmap b5 = BitmapFactory.decodeResource(getResources(), R.drawable.david_seamn_arsenal);
                saveToExternalMemory(b5, "David Seaman");
                Bitmap b6 = BitmapFactory.decodeResource(getResources(), R.drawable.denis_bergkamp);
                saveToExternalMemory(b6, "Dennis Bergkamp");
                Bitmap b7 = BitmapFactory.decodeResource(getResources(), R.drawable.fabregas);
                saveToExternalMemory(b7, "Cesc Fabregas");
                Bitmap b8 = BitmapFactory.decodeResource(getResources(), R.drawable.gael_clichy);
                saveToExternalMemory(b8, "Gaël Clichy");
                Bitmap b9 = BitmapFactory.decodeResource(getResources(), R.drawable.granit_xhaka);
                saveToExternalMemory(b9, "Granit Xhaka");
                Bitmap b10 = BitmapFactory.decodeResource(getResources(), R.drawable.jack_wilshere);
                saveToExternalMemory(b10, "Jack Wilshere");
                Bitmap b11 = BitmapFactory.decodeResource(getResources(), R.drawable.lehmann);
                saveToExternalMemory(b11, "Jens Lehmann");
                Bitmap b12 = BitmapFactory.decodeResource(getResources(), R.drawable.manel_almunia);
                saveToExternalMemory(b12, "Manuel Almunia");
                Bitmap b13 = BitmapFactory.decodeResource(getResources(), R.drawable.nacho_monreal);
                saveToExternalMemory(b13, "Nacho Monreal");
                Bitmap b15 = BitmapFactory.decodeResource(getResources(), R.drawable.sol_campbell);
                saveToExternalMemory(b15, "Sol Campbell");
                Bitmap b14 = BitmapFactory.decodeResource(getResources(), R.drawable.pires);
                saveToExternalMemory(b14, "Robert Pires");
                Bitmap b16 = BitmapFactory.decodeResource(getResources(), R.drawable.thierry_henry);
                saveToExternalMemory(b16, "Thierry Henry");
                Bitmap b17 = BitmapFactory.decodeResource(getResources(), R.drawable.tomas);
                saveToExternalMemory(b17, "Tomas Rosicky");
                Bitmap b18 = BitmapFactory.decodeResource(getResources(), R.drawable.tony_adams);
                saveToExternalMemory(b18, "Tony Adams");
                Bitmap b19 = BitmapFactory.decodeResource(getResources(), R.drawable.vieira);
                saveToExternalMemory(b19, "Patrick Vieira");

                Player Ashley = new Player(getString(R.string.p1), getString(R.string.left_defender), getString(R.string.dp1), getPicturePath("Ashley Cole"));
                myListPlayers.add(Ashley);
                Player Adebayor = new Player(getString(R.string.p2), getString(R.string.striker), getString(R.string.dp2), getPicturePath("Emmanuel Adebayor"));
                myListPlayers.add(Adebayor);
                Player Sagna = new Player(getString(R.string.p3), getString(R.string.right_defender), getString(R.string.dp3), getPicturePath("Bacary Sagna"));
                myListPlayers.add(Sagna);
                Player Bendtner = new Player(getString(R.string.p4), getString(R.string.striker), getString(R.string.dp4), getPicturePath("Nicklas Bendtner"));
                myListPlayers.add(Bendtner);
                Player Seaman = new Player(getString(R.string.p5), getString(R.string.goal_keeper), getString(R.string.dp5), getPicturePath("David Seaman"));
                myListPlayers.add(Seaman);
                Player Bergkamp = new Player(getString(R.string.p6), getString(R.string.striker), getString(R.string.dp6), getPicturePath("Dennis Bergkamp"));
                myListPlayers.add(Bergkamp);
                Player Fabregas = new Player(getString(R.string.p7), getString(R.string.central_midfielder), getString(R.string.dp7), getPicturePath("Cesc Fabregas"));
                myListPlayers.add(Fabregas);
                Player Clichy = new Player(getString(R.string.p8), getString(R.string.left_defender), getString(R.string.dp8), getPicturePath("Gaël Clichy"));
                myListPlayers.add(Clichy);
                Player Xhaka = new Player(getString(R.string.p9), getString(R.string.central_midfielder), getString(R.string.dp9), getPicturePath("Granit Xhaka"));
                myListPlayers.add(Xhaka);
                Player Wilshere = new Player(getString(R.string.p10), getString(R.string.central_midfielder), getString(R.string.dp10), getPicturePath("Jack Wilshere"));
                myListPlayers.add(Wilshere);
                Player Lehmann = new Player(getString(R.string.p11), getString(R.string.goal_keeper), getString(R.string.dp11), getPicturePath("Jens Lehmann"));
                myListPlayers.add(Lehmann);
                Player Almunia = new Player(getString(R.string.p12), getString(R.string.goal_keeper), getString(R.string.dp12), getPicturePath("Manuel Almunia"));
                myListPlayers.add(Almunia);
                Player Monreal = new Player(getString(R.string.p13), getString(R.string.right_defender), getString(R.string.dp13), getPicturePath("Nacho Monreal"));
                myListPlayers.add(Monreal);
                Player Pires = new Player(getString(R.string.p14), getString(R.string.left_midfielder), getString(R.string.dp14), getPicturePath("Robert Pires"));
                myListPlayers.add(Pires);
                Player Campbell = new Player(getString(R.string.p15), getString(R.string.central_defender), getString(R.string.dp15), getPicturePath("Sol Campbell"));
                myListPlayers.add(Campbell);
                Player Henry = new Player(getString(R.string.p16), getString(R.string.left_forward), getString(R.string.dp16), getPicturePath("Thierry Henry"));
                myListPlayers.add(Henry);
                Player Rosicky = new Player(getString(R.string.p17), getString(R.string.right_midfielder), getString(R.string.dp17), getPicturePath("Tomas Rosicky"));
                myListPlayers.add(Rosicky);
                Player Adams = new Player(getString(R.string.p18), getString(R.string.central_defender), getString(R.string.dp18), getPicturePath("Tony Adams"));
                myListPlayers.add(Adams);
                Player Vieira = new Player(getString(R.string.p19), getString(R.string.central_midfielder), getString(R.string.dp19), getPicturePath("Patrick Vieira"));
                myListPlayers.add(Vieira);

                recyclerViewAdapter = new RecyclerViewAdapter(myListPlayers, getBaseContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(recyclerViewAdapter);


            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveToExternalMemory(Bitmap bitmap, String name) {

        File file;

        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path + "/Arsenal FC/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(dir, name + ".jpg");

        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getPicturePath(String fileName) {

        File file = new File(directory, fileName + ".jpg");
        myCurrentFilePath = file.getAbsolutePath();
        return myCurrentFilePath;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                int number=data.getIntExtra("number",0);

                if (data.getStringExtra(PlayersDetail.INTENT_TAG_PLAYER_NAME) != null) {
                    myListPlayers.get(number).setName(data.getStringExtra(PlayersDetail.INTENT_TAG_PLAYER_NAME));
                }
                if (data.getStringExtra(PlayersDetail.INTENT_TAG_PLAYER_POSITION) != null) {
                    myListPlayers.get(number).setPosition(data.getStringExtra(PlayersDetail.INTENT_TAG_PLAYER_POSITION));
                }
                if (data.getStringExtra(PlayersDetail.INTENT_TAG_PLAYER_DESCRIPTION) != null) {
                    myListPlayers.get(number).setDescription(data.getStringExtra(PlayersDetail.INTENT_TAG_PLAYER_DESCRIPTION));
                }
                recyclerViewAdapter.notifyDataSetChanged();

            }
        }
    }


}

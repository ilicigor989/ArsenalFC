package com.example.arsenalfc;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayersDetail extends AppCompatActivity {

    Dialog editDialog;
    EditText changeName;
    EditText changePosition;
    EditText changeDescription;
    TextView descriptionTextView;
    TextView fullNameTextView;
    TextView positionTextView;
    Button submitButton;
    Player player;

    public static final String INTENT_TAG_PLAYER_NAME = "new_player_name";
    public static final String INTENT_TAG_PLAYER_POSITION = "new_player_position";
    public static final String INTENT_TAG_PLAYER_DESCRIPTION = "new_player_description";
    int serilaNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editDialog = new Dialog(this);

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");
        String imageTransitionName = intent.getStringExtra(RecyclerViewAdapter.EXTRA_IMAGE_TRANSITION_NAME);
        String nameTransitionName = intent.getStringExtra(RecyclerViewAdapter.EXTRA_NAME_TRANSITION_NAME);
        String positionTransitionName = intent.getStringExtra(RecyclerViewAdapter.EXTRA_POSITION_TRANSITION_NAME);
        serilaNumber=intent.getIntExtra("serial number",0);

        descriptionTextView = findViewById(R.id.description);
        fullNameTextView = findViewById(R.id.fullName);
        fullNameTextView.setTransitionName(nameTransitionName);
        positionTextView = findViewById(R.id.position);
        positionTextView.setTransitionName(positionTransitionName);
        ImageView mainImage = findViewById(R.id.bigPicture);

        mainImage.setTransitionName(imageTransitionName);

        fullNameTextView.setText(player.getName());
        descriptionTextView.setText(player.getDescription());
        positionTextView.setText(player.getPosition());

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(player.getBitmapPath(), bmOptions);
        mainImage.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.editPlayer) {

            TextView titleText;
            editDialog.setContentView(R.layout.edit_player);

            submitButton = editDialog.findViewById(R.id.changeButton);


            changeName = editDialog.findViewById(R.id.changeName);
            changePosition = editDialog.findViewById(R.id.changePosition);
            changeDescription = editDialog.findViewById(R.id.changeDescription);

            titleText = (TextView) editDialog.findViewById(R.id.title);
            titleText.setText("Edit " + player.getName());



            editDialog.show();
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (changeName.getText().toString().trim().length() != 0
                            &&changePosition.getText().toString().trim().length() != 0
                            &&changeDescription.getText().toString().trim().length() != 0) {

                        player.setName(changeName.getText().toString());
                        player.setPosition(changePosition.getText().toString());
                        player.setDescription(changeDescription.getText().toString());

                        Intent intent = new Intent(getBaseContext(), MainActivity.class);

                        intent.putExtra(INTENT_TAG_PLAYER_NAME, changeName.getText().toString());

                        intent.putExtra(INTENT_TAG_PLAYER_POSITION, changePosition.getText().toString());

                        intent.putExtra(INTENT_TAG_PLAYER_DESCRIPTION, changeDescription.getText().toString());

                        intent.putExtra("number",serilaNumber);

                        setResult(RESULT_OK, intent);
                    }

                    editDialog.dismiss();
                    fullNameTextView.setText(player.getName());
                    descriptionTextView.setText(player.getDescription());
                    positionTextView.setText(player.getPosition());

                }


            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ChangeBounds enterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(2000);

        return bounds;
    }

    private Transition returnTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setInterpolator(new DecelerateInterpolator());
        bounds.setDuration(2000);

        return bounds;
    }


}

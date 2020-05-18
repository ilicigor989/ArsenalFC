package com.example.arsenalfc;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import static android.content.ContentValues.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Player> myPlayers;
    Context mContext;
    private static final int REQUEST_CODE = 1;
    static final String EXTRA_IMAGE_TRANSITION_NAME = "TRANSITION NAME";
    static final String EXTRA_NAME_TRANSITION_NAME = "TRANSITION IMAGE";
    static final String EXTRA_POSITION_TRANSITION_NAME = "TRANSITION POSITION";
    public static int serialNumber;


    public RecyclerViewAdapter(List<Player> myPlayers, Context context) {
        this.myPlayers = myPlayers;
        mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        mContext = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.playerName.setText(myPlayers.get(i).getName());
        viewHolder.playerPosition.setText(myPlayers.get(i).getPosition());

        viewHolder.playerImage.setTransitionName(myPlayers.get(i).getName() + "image");
        viewHolder.playerName.setTransitionName(myPlayers.get(i).getName() + "name");
        viewHolder.playerPosition.setTransitionName(myPlayers.get(i).getName() + "position");

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(myPlayers.get(i).getBitmapPath(), bmOptions);
        viewHolder.playerImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return myPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView playerName;
        TextView playerPosition;
        ImageView playerImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playerName = itemView.findViewById(R.id.text1);
            playerPosition = itemView.findViewById(R.id.text2);
            playerImage = itemView.findViewById(R.id.item_imageview);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(mContext, PlayersDetail.class);

            serialNumber = getAdapterPosition();

            intent.putExtra(EXTRA_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(playerImage));
            intent.putExtra(EXTRA_NAME_TRANSITION_NAME, ViewCompat.getTransitionName(playerName));
            intent.putExtra(EXTRA_POSITION_TRANSITION_NAME, ViewCompat.getTransitionName(playerPosition));


            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(playerName, playerName.getTransitionName());
            pairs[1] = new Pair<View, String>(playerPosition, playerPosition.getTransitionName());
            pairs[2] = new Pair<View, String>(playerImage, playerImage.getTransitionName());

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, pairs);

            ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE, options.toBundle());

        }


    }


}
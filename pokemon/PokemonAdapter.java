package edu.volstate.wade_colton_assign11;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import models.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    //DO NOT change anything here without talking to me. If you have a software engineering idea
    //to make this better, then make those changes and show it to me. :)

    //DO NOT modify this code because you think it's why your code isn't working. This code
    //works properly--see the Zoom session.

    private ArrayList<Pokemon> dataset;
    private Context context;
    private final String BASE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    public PokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.numberTextView.setText(p.getName());

        //We are getting images with Glide. This works because I added Glide to the
        //Module build.gradle file. Google Glide for more information. You will
        //modify this code at a later date, BUT NOT IN THIS ASSIGNMENT.
        Glide.with(context)
                .load(BASE_IMAGE_URL + p.getId() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photoImageView);

        // Builds AlertDialog
        holder.pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(view.getRootView().getContext());

                View dialogView = LayoutInflater.from(view.getRootView().
                        getContext()).inflate(R.layout.pokemon, null);

                ImageView pokeAvatar = (ImageView) dialogView.findViewById(R.id.imageViewPokemon);
                TextView pokeName = (TextView) dialogView.findViewById(R.id.textViewName);
                TextView pokeBaseExperience = (TextView) dialogView.findViewById(R.id.textViewBaseE);
                TextView pokeID = (TextView) dialogView.findViewById(R.id.textViewID);
                TextView pokeHeightWeight = (TextView) dialogView.findViewById(R.id.textViewHeightWeight);
                TextView pokeAbility = (TextView) dialogView.findViewById(R.id.textViewAbilities);

                Glide.with(context)
                        .load(BASE_IMAGE_URL + p.getId() + ".png")
                        .into(pokeAvatar);

                pokeName.setText(String.format("Pokemon: %s", p.getName()));
                pokeBaseExperience.setText(String.format("Base Experience: %s", p.getBaseExperience()));
                pokeID.setText(String.format("ID: %s", p.getId()));
                pokeHeightWeight.setText(String.format("Height: %S, Weight: %s", p.getHeight(), p.getWeight()));
                pokeAbility.setText(String.format("Abilities: %s", p.getAbilities()));


                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addListPokemon(ArrayList<Pokemon> pokemonList) {
        dataset.clear();
        dataset.addAll(pokemonList);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoImageView;
        private TextView numberTextView;
        private LinearLayout pokemon;

        public ViewHolder(View itemView) {
            super(itemView);

            pokemon = (LinearLayout) itemView.findViewById(R.id.list_item) ;
            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            numberTextView = (TextView) itemView.findViewById(R.id.numberTextView);
        }
    }
}
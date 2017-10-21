package net.technoverse.sithu.darna.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.technoverse.sithu.darna.MainApplication;
import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.gmodel.ModelThadin;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

/**
 * Created by sithu on 10/22/17.
 */

public class ThadinAdapter extends RecyclerView.Adapter<ThadinAdapter.MainViewHolder> {


    Context context;
    List<ModelThadin> thadins;
    MainItemClickListener mListener;

    public ThadinAdapter(Context context, List<ModelThadin> thadins, MainItemClickListener mListener) {
        this.context = context;
        this.thadins = thadins;
        this.mListener = mListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Connecting with cardview (view)
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.organization_card, parent, false );
        return new MainViewHolder( view );
    }

    @Override
    public void onBindViewHolder(ThadinAdapter.MainViewHolder holder, int position) {

        SharedPreferences sharedPreferences;
        sharedPreferences = MainApplication.get().getSharePre(context);

        if (!sharedPreferences.getBoolean( Const.CAN_SEE_UNICODE, true )) {
            TypefaceManager typefaceManager;
            typefaceManager = new TypefaceManager( context.getAssets() );
            holder.textView_title.setTypeface( typefaceManager.getShitUnicde() );
        }

        holder.textView_title.setText(thadins.get(position).getTitle());

        Glide
                .with( context )
                .load(thadins.get( position ).getImage_url())
                .transition(withCrossFade())
                .apply(fitCenterTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return thadins.size();
    }

    public interface MainItemClickListener {
        public void onItemClick(ModelThadin itemClicked);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView_title;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.logo_and_header_image);
            this.textView_title = (TextView) itemView.findViewById(R.id.txt_title);
            itemView.setOnClickListener(this);
        }

        @Override

        public void onClick(View v) {
            mListener.onItemClick(thadins.get(getLayoutPosition()));
        }
    }
}

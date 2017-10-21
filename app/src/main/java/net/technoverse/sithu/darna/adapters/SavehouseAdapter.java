package net.technoverse.sithu.darna.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.MainApplication;
import net.technoverse.sithu.darna.OnLoadMoreListener;
import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.gmodel.ModelSavehouse;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

/**
 * Created by sithu on 6/27/17.
 */

public class SavehouseAdapter extends RecyclerView.Adapter<SavehouseAdapter.MainViewHolder> {

    Context context;
    List<ModelSavehouse> savehouses;
    MainItemClickListener mListener;
//    TypefaceManager typefaceManager;


    //for loadmore
//    private final int VIEW_TYPE_ITEM = 0;
//    private final int VIEW_TYPE_LOADING = 1;
//    private OnLoadMoreListener mOnLoadMoreListener;
//    private boolean isLoading;private int visibleThreshold = 5;
//    private int lastVisibleItem,totalItemCount;



    public SavehouseAdapter(Context context, List<ModelSavehouse> savehouses, MainItemClickListener mListener) {
        this.context = context;
        this.savehouses = savehouses;
        this.mListener = mListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Connecting with cardview (view)
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.savehouse_card, parent, false );
        return new MainViewHolder( view );
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        SharedPreferences sharedPreferences;
        sharedPreferences = MainApplication.get().getSharePre(context);

        if (!sharedPreferences.getBoolean( Const.CAN_SEE_UNICODE, true )) {
            TypefaceManager typefaceManager;
            typefaceManager = new TypefaceManager( context.getAssets() );
            holder.textView_title.setTypeface( typefaceManager.getShitUnicde() );
            holder.textView_township.setTypeface( typefaceManager.getShitUnicde() );
            holder.textView_block.setTypeface( typefaceManager.getShitUnicde() );
        }

        holder.textView_title.setText(savehouses.get(position).getName());
        holder.textView_township.setText(savehouses.get(position).getTownship());
        holder.textView_block.setText(savehouses.get(position).getBlock());

        Glide
                .with( context )
                .load(savehouses.get( position ).getImageUrl())
                .transition(withCrossFade())
                .apply(fitCenterTransform())
                .into(holder.imageView);

//                .with( context )
//                .load( savehouses.get( position ).getImage_url() )
//                .fitCenter()
//                .override(300, 200)
//                .placeholder( R.drawable.organ_photo )
//                .crossFade()
//                .into( holder.image_logo );
    }

    @Override
    public int getItemCount() {
        return savehouses.size();
    }


    public interface MainItemClickListener {
        public void onItemClick(ModelSavehouse itemClicked);
    }



    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView_title,textView_township,textView_block;

        public MainViewHolder(View itemView) {
            super( itemView );
            imageView = (ImageView) itemView.findViewById( R.id.logo_and_header_image );
            textView_title = (TextView) itemView.findViewById( R.id.txt_title );
            textView_township = (TextView) itemView.findViewById( R.id.txt_township );
            textView_block = (TextView) itemView.findViewById( R.id.txt_block );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(savehouses.get(getLayoutPosition()));
        }



    }
}

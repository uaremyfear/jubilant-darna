package net.technoverse.sithu.darna.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.technoverse.sithu.darna.MainApplication;
import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.gmodel.ModelCategory;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;


import java.util.List;

/**
 * Created by sithu on 6/30/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MainViewHolder> {

    Context context;
    List<ModelCategory> categories;
    MainItemClickListener mListener;

    public CategoryAdapter(Context context, List<ModelCategory> categories, MainItemClickListener mListener) {
        this.context = context;
        this.categories = categories;
        this.mListener = mListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.category_card, parent, false );
        return new MainViewHolder( view );
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        SharedPreferences sharedPreferences;
        sharedPreferences = MainApplication.get().getSharePre(context);

        if (!sharedPreferences.getBoolean( Const.CAN_SEE_UNICODE, true )) {
            TypefaceManager typefaceManager;
            typefaceManager = new TypefaceManager( context.getAssets() );
            holder.textView_Image.setTypeface( typefaceManager.getShitUnicde() );
            holder.textView_CategoryName.setTypeface( typefaceManager.getShitUnicde() );
        }

        holder.textView_Image.setText(String.valueOf(categories.get(position).getCategory_name().charAt(0)));
        holder.textView_CategoryName.setText(categories.get(position).getCategory_name());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public interface MainItemClickListener {
        public void onItemClick(ModelCategory itemClicked);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView_Image,textView_CategoryName;

        public MainViewHolder(View itemView) {
            super( itemView );
            textView_Image = (TextView) itemView.findViewById( R.id.txt_image );
            textView_CategoryName = (TextView) itemView.findViewById( R.id.txt_category_name );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(categories.get(getLayoutPosition()));
        }
    }
}

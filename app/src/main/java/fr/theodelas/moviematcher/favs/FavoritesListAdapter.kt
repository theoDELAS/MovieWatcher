package fr.theodelas.moviematcher.favs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.theodelas.moviematcher.R
import fr.theodelas.moviematcher.home.HomeCardModel


internal class FavoritesListAdapter(private var itemsList: List<HomeCardModel>) : RecyclerView.Adapter<FavoritesListAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTextView: TextView = view.findViewById(R.id.title)
        var releaseDateTextView: TextView = view.findViewById(R.id.releaseDate)
        var imageView: ImageView = view.findViewById(R.id.imageView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val favItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fav_item, parent, false)
        return MyViewHolder(favItemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fav = itemsList[position]
        holder.titleTextView.text = fav.title
        holder.releaseDateTextView.text = fav.releaseDate
        holder.imageView.load(fav.imagePath) {
            crossfade(true)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
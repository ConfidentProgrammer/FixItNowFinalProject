package project.st991587084.JeelDhruvDeep


import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.services_item.view.*
import project.st991587084.JeelDhruvDeep.MainActivity
import java.lang.Integer.parseInt

class MyRecyclerView (private val sampleList: List <Services>): RecyclerView.Adapter <MyRecyclerView.MyViewHolder>() {

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val imageView: ImageView=itemView.image_view
        val serviceName: TextView =itemView.serviceName
        val rl : RelativeLayout = itemView.rl
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.services_item,
            parent, false)

        //adding background color
        itemView.setBackgroundColor(Color.parseColor("#CAF1DE"))
        //adding elevation
        itemView.elevation = 5f

        return MyViewHolder(itemView)
    }

    override fun getItemCount() = sampleList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = sampleList [position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.serviceName.text= currentItem.serviceName
        holder.rl.setOnClickListener {
            Toast.makeText(holder.rl.context
                , "${currentItem.serviceName}" ,
                Toast.LENGTH_SHORT).show()
        }

    }


}
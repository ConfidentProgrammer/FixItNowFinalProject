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
import kotlinx.android.synthetic.main.request_item.view.*
import kotlinx.android.synthetic.main.services_item.view.*
import kotlinx.android.synthetic.main.services_item.view.rl
import project.st991587084.JeelDhruvDeep.MainActivity
import java.lang.Integer.parseInt

class RequestRecyclerView (private val sampleList: List <Requests>): RecyclerView.Adapter <RequestRecyclerView.MyViewHolder>() {

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val imageView: ImageView=itemView.img_view
        val email: TextView =itemView.email
        val phone: TextView = itemView.phone
        val description: TextView = itemView.description
        val location: TextView = itemView.location
        val rl : RelativeLayout = itemView.rl
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.request_item,
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
        holder.email.text = (currentItem.Email)
        holder.phone.text = currentItem.Phone.toString()
        holder.description.text = currentItem.Description
        holder.location.text = currentItem.Location


        holder.rl.setOnClickListener {
            Toast.makeText(holder.rl.context
                , "${currentItem.Email}" ,
                Toast.LENGTH_SHORT).show()
        }

    }




}
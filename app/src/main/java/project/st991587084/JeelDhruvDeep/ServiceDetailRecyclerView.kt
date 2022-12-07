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
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.api.Context
import kotlinx.android.synthetic.main.request_item.view.*
import kotlinx.android.synthetic.main.services_item.view.*
import kotlinx.android.synthetic.main.services_item.view.rl
import project.st991587084.JeelDhruvDeep.MainActivity
import java.lang.Integer.parseInt

class ServiceDetailRecyclerView(private val sampleList: ArrayList <ServiceDetail>, val context: android.content.Context): RecyclerView.Adapter <ServiceDetailRecyclerView.MyViewHolder>() {

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        //  val imageView: ImageView=itemView.img_view
        val experience: TextView = itemView.experience
        val phone: TextView = itemView.phone
        val email: TextView =itemView.email
        val name: TextView = itemView.name
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
//        holder.imageView.setImageResource(currentItem.imageResource)
        holder.email.text = "Email: "+(currentItem.Email)
        holder.phone.text = "Phone: "+currentItem.Phone.toString()
        holder.name.text = "Name: "+currentItem.Name
        holder.experience.text = "Experience: "+currentItem.Experience.toString()


        holder.rl.setOnClickListener {
            Toast.makeText(holder.rl.context
                , "${currentItem.Email}" ,
                Toast.LENGTH_SHORT).show()


        }

    }




}
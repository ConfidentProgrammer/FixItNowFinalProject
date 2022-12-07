package project.st991587084.JeelDhruvDeep


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.request_item.view.*
import kotlinx.android.synthetic.main.services_item.view.rl


class ServiceDetailRecyclerView(private val sampleList: ArrayList <ServiceDetail>, val context: android.content.Context): RecyclerView.Adapter <ServiceDetailRecyclerView.MyViewHolder>() {

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val experience: TextView = itemView.experience
        val phone: TextView = itemView.phone
        val email: TextView =itemView.email
        val name: TextView = itemView.name
        val rl : RelativeLayout = itemView.rl
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.service_detail_item,
            parent, false)

        //adding background color
        itemView.setBackgroundColor(Color.parseColor("#CAF1DE"))
        itemView.elevation = 5f

        return MyViewHolder(itemView)
    }

    override fun getItemCount() = sampleList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = sampleList [position]
        holder.email.text = "Email: "+(currentItem.Email)
        holder.phone.text = "Phone: "+currentItem.Phone.toString()
        holder.name.text = "Name: "+currentItem.Name
        holder.experience.text = "Experience: "+currentItem.Experience.toString()


        holder.rl.setOnClickListener {
            Toast.makeText(holder.rl.context
                , "${currentItem.Name}" ,
                Toast.LENGTH_SHORT).show()

        }
    }
}
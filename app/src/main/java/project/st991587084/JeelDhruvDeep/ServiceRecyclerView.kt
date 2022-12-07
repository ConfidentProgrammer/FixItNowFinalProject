package project.st991587084.JeelDhruvDeep


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.services_item.view.*

class MyRecyclerView(private val sampleList: List<Services>): RecyclerView.Adapter <MyRecyclerView.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imageView: ImageView = itemView.image_view
        val serviceName: TextView = itemView.serviceName
        val rl: RelativeLayout = itemView.rl
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.services_item,
            parent, false)

        //adding background color
        itemView.setBackgroundColor(Color.parseColor("#CAF1DE"))
        //adding elevation
        itemView.elevation = 5f
        itemView.setOnClickListener{

        }
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = sampleList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = sampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.serviceName.text = currentItem.serviceName
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.rl.context, "${currentItem.serviceName}",
                Toast.LENGTH_SHORT).show()
//            var activity: AppCompatActivity = it.context as AppCompatActivity
//            var filter: FilterService = FilterService()
//            activity.supportFragmentManager.beginTransaction().replace(R.id.temp, filter)
//                .addToBackStack(null).commit()


        }


        holder.itemView.setOnClickListener( View.OnClickListener { view ->
            Toast.makeText(holder.rl.context, "${currentItem.serviceName}",
                Toast.LENGTH_SHORT).show()
            var activity  = view.context as AppCompatActivity
            var filter = FilterService()

            val bundle = Bundle()
            val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
            filter.arguments = bundle;
            fragmentTransaction.replace(R.id.temp, filter).addToBackStack(null).commit()
//            activity.supportFragmentManager.beginTransaction().replace(R.id.temp, filter)
//                .addToBackStack(null).commit()
            bundle.putString("name", currentItem.serviceName)

        })

    }
}
package project.st991587084.JeelDhruvDeep


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.manage_request_item.view.*
import kotlinx.android.synthetic.main.request_item.view.*
import kotlinx.android.synthetic.main.request_item.view.email
import kotlinx.android.synthetic.main.request_item.view.experience
import kotlinx.android.synthetic.main.request_item.view.name
import kotlinx.android.synthetic.main.request_item.view.phone
import kotlinx.android.synthetic.main.services_item.view.rl

class ManageRequestRecyclerView(private val sampleList: ArrayList <ManageRequests>, val context: android.content.Context): RecyclerView.Adapter <ManageRequestRecyclerView.MyViewHolder>() {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

      //  val imageView: ImageView=itemView.img_view
      val description: TextView = itemView.experience
        val phone: TextView = itemView.phone
        val email: TextView =itemView.email
        val location: TextView = itemView.name
        val rl : RelativeLayout = itemView.rl
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.manage_request_item,
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
        holder.email.text = (currentItem.Email)
        holder.phone.text = currentItem.Phone.toString()
        holder.description.text = currentItem.Description
        holder.location.text = currentItem.Location


        holder.rl.setOnClickListener {
            Toast.makeText(holder.rl.context
                , "${currentItem.Email}" ,
                Toast.LENGTH_SHORT).show()
        }
        holder.rl.deleteReq.setOnClickListener {

            //deleting the request

            val query = fireStoreDatabase.collection("RequestAndroid")
             .whereEqualTo("Description", currentItem.Description.toString())
                .get()

            query.addOnSuccessListener {
                for(document in it){
                    fireStoreDatabase.collection("RequestAndroid").document(document.id).delete()
                        .addOnSuccessListener {
                            Log.d("Deleted"," document deleted with")
                        }
                }
            }
            query.addOnFailureListener{
                Log.d(""," document deleted with")

            }
            Toast.makeText(holder.rl.context
                , "Request Deleted" ,
                Toast.LENGTH_SHORT).show()
        }

    }




}
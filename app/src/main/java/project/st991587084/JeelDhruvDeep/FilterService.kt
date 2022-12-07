package project.st991587084.JeelDhruvDeep

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_customer_login.*
import project.st991587084.JeelDhruvDeep.databinding.FragmentFilterServiceBinding
import project.st991587084.JeelDhruvDeep.databinding.FragmentProfessionalLoginBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FilterService : Fragment() {

    private var _binding: FragmentFilterServiceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val fireStoreDatabase = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFilterServiceBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAllData()
    }


    fun loadAllData(){
       // val req = ArrayList<Requests>()
//        fireStoreDatabase.collection("RequestAndroid")
//            .get()
//            .addOnSuccessListener {
//                if(it.isEmpty){
//                    Toast.makeText(this.context, "not found", Toast.LENGTH_SHORT).show()
//                    return@addOnSuccessListener
//                }
//
//                for(doc in it){
//                    val request = doc.toObject(Requests::class.java)
//                    req.add(request)
//                }
//                binding.recycleView.apply {
//                    layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
//                    adapter = RequestRecyclerView(req,this.context)
//                }
//            }




        //query
        var mBundle: Bundle? = arguments
        var serviceName = mBundle!!.getString("name")
        val req = ArrayList<ServiceDetail>()

            val query = fireStoreDatabase.collection("ProfessionalProfileAndroid")
                .whereEqualTo("Service", serviceName)
                .get()

            query.addOnSuccessListener {
                for(doc in it){
                    val request = doc.toObject(ServiceDetail::class.java)
                    req.add(request)
                }
                binding.recycleView.apply {
                    layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
                    adapter = ServiceDetailRecyclerView(req,this.context)
                }
                }
            }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
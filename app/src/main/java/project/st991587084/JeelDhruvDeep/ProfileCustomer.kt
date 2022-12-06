package project.st991587084.JeelDhruvDeep

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_customer_login.*
import project.st991587084.JeelDhruvDeep.databinding.FragmentCustomerLoginBinding
import project.st991587084.JeelDhruvDeep.databinding.FragmentProfileCustomerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCustomer.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCustomer : Fragment() { // fragment for Customer profile
    // TODO: Rename and change types of parameters

    private var _binding: FragmentProfileCustomerBinding? = null
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProfileCustomerBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Fetching Customer Profile
        binding!!.profileEmail.setText(user?.email).toString()
        fireStoreDatabase.collection("CustomerProfileAndroid")
            .get()
            .addOnCompleteListener{
                val result : StringBuffer = StringBuffer()
                if(it.isSuccessful) {
                    for(document in it.result!!) {

                        if(user?.email.toString() == document.data.getValue("Email").toString()){
                            binding!!.profilePhone.setText(document.data.getValue("Phone").toString())
                            binding!!.profileName.setText(document.data.getValue("Name").toString())
                            binding!!.profileAddress.setText(document.data.getValue("Address").toString())
                            binding!!.profileEmail.setText(user?.email).toString()
                            println(document.data.getValue("Phone").toString() + "Phone")
                        }

                    }
                }

            }



        //Creating or updating Profile
        super.onViewCreated(view, savedInstanceState)
        binding.savebtn.setOnClickListener{
            var emailpr : String = user?.email.toString()
            var phonepr : Int = binding!!.profilePhone.text.toString().toInt()
            var namepr : String = binding!!.profileName.text.toString()
            var addresspr : String = binding!!.profileAddress.text.toString()



            val proProfile:MutableMap<String, Any> = HashMap()
            proProfile["Email"] = emailpr
            proProfile["Phone"] = phonepr
            proProfile["Name"] = namepr
            proProfile["Address"] = addresspr

            fireStoreDatabase.collection("CustomerProfileAndroid")
                .document(it.id.toString()).set(proProfile)
                .addOnSuccessListener {
                    Log.d("DocMsg", "Added document ${it}")
                    Toast.makeText(this.context, "Profile Created", Toast.LENGTH_SHORT).show()
                    println(user?.email)
                }


            findNavController().navigate(R.id.action_profileCustomer_to_customerDashboard)
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCustomer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCustomer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
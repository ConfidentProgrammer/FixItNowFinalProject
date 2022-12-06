package project.st991587084.JeelDhruvDeep

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.request_item.*
import project.st991587084.JeelDhruvDeep.databinding.FragmentProfileProfessionalBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileProfessional.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileProfessional : Fragment() {
    private var _binding: FragmentProfileProfessionalBinding? = null
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // TODO: Rename and change types of parameters
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
        _binding = FragmentProfileProfessionalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var servicepr : String = ""
        val personNames = arrayOf("Electrical Service"
            , "General Construction", "Grass Cutting",
            "Home Cleaning", "Home Repair", "Landscapping", "Laundry", "Pest Control",
            "Plumbing", "Welding", "Window Cleaning")
        val arrayAdaptor = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, personNames)

        binding.spinner2.adapter = arrayAdaptor

        binding.spinner2.onItemSelectedListener =object:
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                servicepr = personNames[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
        binding.savebtn.setOnClickListener{

            var emailpr : String = user?.email.toString()
            var phonepr : Int = binding!!.proPhone.text.toString().toInt()
            var namepr : String = binding!!.proName.text.toString()
            var exppr : Int = binding!!.ProExp.text.toString().toInt()



            val proProfile:MutableMap<String, Any> = HashMap()
            proProfile["Email"] = emailpr
            proProfile["Phone"] = phonepr
            proProfile["Name"] = namepr
            proProfile["Experience"] = exppr
            proProfile["Service"] = servicepr

            fireStoreDatabase.collection("ProfessionalProfileAndroid")
                .document(it.id.toString()).set(proProfile)
                .addOnSuccessListener {
                    Log.d("DocMsg", "Added document ${it}")
                    Toast.makeText(this.context, "Profile Created", Toast.LENGTH_SHORT).show()
                    println(user?.email)
                }


            binding!!.proName.text.clear()
            binding!!.proPhone.text.clear()
            binding!!.ProExp.text.clear()

            findNavController().navigate(R.id.action_profileProfessional_to_professionalLogin)
        }


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileProfessional.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileProfessional().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
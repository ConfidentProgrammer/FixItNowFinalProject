package project.st991587084.JeelDhruvDeep

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import project.st991587084.JeelDhruvDeep.databinding.FragmentPostRequestBinding
import project.st991587084.JeelDhruvDeep.databinding.FragmentProfileCustomerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostRequest.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostRequest : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPostRequestBinding? = null

    val fireStoreDatabase = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostRequestBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.emailpr.setText(user?.email).toString()
        binding.savebtn.setOnClickListener{

            var emailpr : String = user?.email.toString()
            var phonepr : Int = binding!!.phonepr.text.toString().toInt()
            var locationpr : String = binding!!.locationpr.text.toString()
            var descpr : String = binding!!.descpr.text.toString()
            val request:MutableMap<String, Any> = HashMap()
            request["Email"] = emailpr
            request["Phone"] = phonepr
            request["Location"] = locationpr
            request["Description"] = descpr

            fireStoreDatabase.collection("RequestAndroid")
                .add(request)
                .addOnSuccessListener {
                    Log.d("DocMsg", "Added document ${it}")
                    Toast.makeText(this.context, "Request Posted", Toast.LENGTH_SHORT).show()
                }


            binding!!.phonepr.text.clear()
            binding!!.descpr.text.clear()
            binding!!.locationpr.text.clear()

            //redirecting to the dashboard
            findNavController().navigate(R.id.action_postRequest_to_customerDashboard3)
        }

        //for spinner  - professional experience


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostRequest.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostRequest().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
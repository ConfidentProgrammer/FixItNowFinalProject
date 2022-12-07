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
import project.st991587084.JeelDhruvDeep.databinding.FragmentProfessionalLoginBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProfessionalLogin : Fragment() {

    private var _binding: FragmentProfessionalLoginBinding? = null

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

        _binding = FragmentProfessionalLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.professionalSignout.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext())
            findNavController().navigate(R.id.action_professionalLogin_to_FirstFragment)
        }
        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_professionalLogin_to_profileProfessional)
        }

        loadAllData()
    }


    fun loadAllData(){
        val req = ArrayList<Requests>()
        fireStoreDatabase.collection("RequestAndroid")
            .get()
            .addOnSuccessListener {
                if(it.isEmpty){
                    Toast.makeText(this.context, "not found", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }

                for(doc in it){
                    val request = doc.toObject(Requests::class.java)
                    req.add(request)
                }
                binding.recycleView.apply {
                    layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
                    adapter = RequestRecyclerView(req,this.context)
                }
            }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
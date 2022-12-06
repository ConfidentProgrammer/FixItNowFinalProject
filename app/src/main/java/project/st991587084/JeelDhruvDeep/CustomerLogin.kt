package project.st991587084.JeelDhruvDeep

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import project.st991587084.JeelDhruvDeep.databinding.FragmentCustomerLoginBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_customer_login.*

//import project.st991590206.JeelDeepDhruv.databinding.FragmentCustomerLoginBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class CustomerLogin : Fragment() {

    private var _binding: FragmentCustomerLoginBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentCustomerLoginBinding.inflate(inflater, container, false)
        return binding.root


    }
    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.title = "Customer Dashboard"
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerSignout.setOnClickListener{
                AuthUI.getInstance().signOut(requireContext())
            findNavController().navigate(R.id.action_customerDashboard_to_FirstFragment)
            }
      binding.imageView.setOnClickListener{

          findNavController().navigate(R.id.action_customerDashboard_to_profileCustomer)
      }
        val ourList = generateDummyList(11)

        recycleView.adapter = MyRecyclerView(ourList)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        recycleView.setHasFixedSize(true)
        recycleView.setBackgroundColor(Color.parseColor("#effafa"))
        binding.postrequest.setOnClickListener{
            findNavController().navigate(R.id.action_customerDashboard_to_postRequest)
        }

        }
    private fun generateDummyList(size: Int): List<Services> {
        //photos
        val photos = arrayOf(R.drawable.electrical, R.drawable.architect, R.drawable.lawn,
            R.drawable.cleaning, R.drawable.home, R.drawable.park, R.drawable.laundry,
            R.drawable.insecticide,R.drawable.plumbing,R.drawable.welding,R.drawable.window)
        //names
        val names = arrayOf("Electrical Service"
            , "General Construction", "Grass Cutting",
            "Home Cleaning", "Home Repair", "Landscapping", "Laundry", "Pest Control",
        "Plumbing", "Welding", "Window Cleaning")
        //weights
        val list = ArrayList<Services>()
        for (i in 0 until size) {
            val drawable = photos[i]
            val item = Services(drawable,
                names[i])
            list += item
        }

        return list
    }


    }

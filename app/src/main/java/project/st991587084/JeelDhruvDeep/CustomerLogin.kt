package project.st991587084.JeelDhruvDeep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import project.st991587084.JeelDhruvDeep.databinding.FragmentCustomerLoginBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInButton.setOnClickListener{
                AuthUI.getInstance().signOut(requireContext())
            findNavController().navigate(R.id.action_customerDashboard_to_FirstFragment)
            }
        }



    }

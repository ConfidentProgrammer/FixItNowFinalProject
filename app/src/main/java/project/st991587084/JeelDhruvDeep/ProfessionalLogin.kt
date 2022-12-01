package project.st991587084.JeelDhruvDeep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import project.st991587084.JeelDhruvDeep.databinding.FragmentProfessionalLoginBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProfessionalLogin : Fragment() {

    private var _binding: FragmentProfessionalLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfessionalLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.professionalSignout.setOnClickListener{
            AuthUI.getInstance().signOut(requireContext())
            findNavController().navigate(R.id.action_professionalLogin_to_FirstFragment)
        }
        binding.imageView.setOnClickListener{
            findNavController().navigate(R.id.action_professionalLogin_to_profileProfessional)
        }
//        binding.CustLogin.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
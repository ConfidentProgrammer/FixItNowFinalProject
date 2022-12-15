package project.st991587084.JeelDhruvDeep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import project.st991587084.JeelDhruvDeep.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    private var AuthState = 1
    private var _binding: FragmentSecondBinding? = null
    companion object {
        const val TAG = "MainFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAuthenticationState()
        binding.CustLogin.setOnClickListener{
            AuthState = 1
            launchSignInFlow()
            Log.d("authState",AuthState.toString())
        }
        binding.ProfLogin.setOnClickListener{
            AuthState = 2
            launchSignInFlow()
            Log.d("authState",AuthState.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SecondFragment.SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // User successfully signed in
                Log.i(
                    SecondFragment.TAG,
                    "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!"
                )
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Log.i(SecondFragment.TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun observeAuthenticationState() {
        val factToDisplay = viewModel.getFactToDisplay(requireContext())

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                     //binding.CustLogin.text = getString(R.string.logout_button_text)
                    if(AuthState == 1) {
                        findNavController().navigate(R.id.action_SecondFragment_to_customerDashboard)
                        Log.d("authState",AuthState.toString())
                    }else if (AuthState == 2) {
                        findNavController().navigate(R.id.action_SecondFragment_to_professionalLogin)
                        Log.d("authState",AuthState.toString())
                    }

                }
                else -> {
                    //    binding.welcomeText.text = factToDisplay
                    binding.CustLogin.text = "Login as Customer"
                    binding.CustLogin.setOnClickListener {
                        launchSignInFlow()
                    }
                }
            }
        })
    }

    private fun launchSignInFlow() {
        // Give users the option to sign in / register with their email
        // If users choose to register with their email,
        // they will need to create a password as well
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
            //
        )

        // Create and launch sign-in intent.
        // We listen to the response of this activity with the
        // SIGN_IN_RESULT_CODE code
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).build(), SecondFragment.SIGN_IN_RESULT_CODE
        )
    }

}
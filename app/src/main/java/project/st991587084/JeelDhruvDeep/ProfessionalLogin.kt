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
        binding.professionalSignout.setOnClickListener{
            AuthUI.getInstance().signOut(requireContext())
            findNavController().navigate(R.id.action_professionalLogin_to_FirstFragment)
        }
        binding.imageView.setOnClickListener{
            findNavController().navigate(R.id.action_professionalLogin_to_profileProfessional)
        }

        loadAllData()


//        fireStoreDatabase.collection("RequestAndroid")
//            .get()
//            .addOnCompleteListener{
//                val result : StringBuffer = StringBuffer()
//                if(it.isSuccessful) {
//                    var temp = 0
//                    for(document in it.result!!) {
//                        temp += 1
//                    }
//
//                    binding.count.text = temp.toString()
//               //
//                }
//                //     binding?.textView?.setText(result)
////                println("size = "+ count)
//                 list = generateDummyList(count)

      //  println("size = "+ binding.count.text)
      //  val list = generateDummyList(binding.count.text.toString().toInt()+1)
//        recycleView.adapter = RequestRecyclerView(list)
//        recycleView.layoutManager = LinearLayoutManager(this.context)
//        recycleView.setHasFixedSize(true)
//        recycleView.setBackgroundColor(Color.parseColor("#effafa"))


//        binding.CustLogin.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
          }


   // private fun generateDummyList(size: Int): List<Requests> {
//        val list = ArrayList<Requests>()
//        var email = arrayListOf<String>("123@gmail.com")
//        val photos = arrayOf(R.drawable.android)
//
//        //reading data from firestore
//
//        fireStoreDatabase.collection("RequestAndroid")
//            .get()
//            .addOnCompleteListener {
//                val result: StringBuffer = StringBuffer()
//                if (it.isSuccessful) {
//                    for (document in it.result!!) {
//
//                        val ema = (document.data.getValue("Email").toString())
//                        email.add(ema)
//
//                        println("email = " + email)
//                        val drawable = R.drawable.android
//                        val item = Requests(
//                            drawable,
//                            "", 123, email.size.toString(), ""
//                        )
//                        list += item
//
//                        //    val description = arrayOf(document.data.getValue(""))
//                    }
//
//                }
//                for (i in 0 until size) {
//                    println("in for")
//                    val drawable = R.drawable.android
//                    val item = Requests(
//                        drawable,
//                        "", 123, email[i], ""
//                    )
//                    list += item
////        }
//                }
//
////        val list = ArrayList<Requests>()
//                //println("123size = " +size)
////        for (i in 0 until size) {
////            val drawable = R.drawable.android
////            val item = Requests(drawable,
////                "",123,email.size.toString(),"")
////            list += item
//
//            }
//        return list

    //}


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
package com.miu.cv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.miu.cv.R
import com.miu.cv.model.User
import kotlinx.android.synthetic.main.edu_bar.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private val KEY = "user"
    private lateinit var user: User

    fun newInstance(user: User): HomeFragment {
        val args = Bundle()
        val fragment = HomeFragment()
        args.putSerializable(KEY, user)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val infl = inflater.inflate(R.layout.fragment_home, container, false)
        user = arguments?.getSerializable(KEY) as User

        infl.name.text = "${user.fist_name} ${user.last_name}"
        infl.title.text = user.position
        infl.about.text = user.about

        val eduBox = infl.findViewById<LinearLayout>(R.id.educations)
        for (edu in user.education) {
            val item = inflater.inflate(R.layout.edu_bar, container, false)
            item.major.text = edu.major
            item.university.text = edu.university
            item.between.text = "${edu.start} - ${edu.end}"
            eduBox.addView(item)
        }

        return infl
    }
}
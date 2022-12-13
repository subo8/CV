package com.miu.cv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.miu.cv.R
import com.miu.cv.model.User
import kotlinx.android.synthetic.main.exp_bar.view.*

class ExperienceFragment : Fragment() {
    private val KEY = "user"
    private lateinit var user: User

    fun newInstance(user: User): ExperienceFragment {
        val args = Bundle()
        val fragment = ExperienceFragment()
        args.putSerializable(KEY, user)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val infl = inflater.inflate(R.layout.fragment_experience, container, false)
        user = arguments?.getSerializable(KEY) as User

        val experienceBox = infl.findViewById<LinearLayout>(R.id.experience)

        for (exp in user.roles) {
            val item = inflater.inflate(R.layout.exp_bar, container, false)
            item.role.text = exp.position
            item.company.text = exp.company
            item.between.text = "${exp.start}-${exp.end}"
            experienceBox.addView(item)
        }
        return infl
    }

}
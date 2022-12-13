package com.miu.cv.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.miu.cv.R
import com.miu.cv.WebView
import com.miu.cv.model.User
import kotlinx.android.synthetic.main.fragment_contact.view.*

class ContactFragment : Fragment() {
    private val KEY = "user"
    private lateinit var user: User

    fun newInstance(user: User): ContactFragment {
        val args = Bundle()
        val fragment = ContactFragment()
        args.putSerializable(KEY, user)
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var infl = inflater.inflate(R.layout.fragment_contact, container, false)
        user = arguments?.getSerializable(KEY) as User
        infl.phone_number.text = "${user.contacts.cellPhone}"
        var phone = infl.findViewById<LinearLayout>(R.id.phone)
        phone.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:${user.contacts.cellPhone}"))
                startActivity(intent)
            }
        })

        var gmail = infl.findViewById<LinearLayout>(R.id.gmail)
        gmail.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val uriText = "mailto:${user.contacts.email}" + "?subject=" + Uri.encode("Hello from ${user.fist_name}") + "&body=" + Uri.encode("This message from my CV app")
                val uri = Uri.parse(uriText)
                val send = Intent(Intent.ACTION_SENDTO)
                send.data = uri
                startActivity(Intent.createChooser(send, "Send"))
            }
        })

        var github = infl.findViewById<LinearLayout>(R.id.github)
        github.setOnClickListener {
            var uri = Uri.parse(user.contacts.gitHub)
            var view = Intent(Intent.ACTION_VIEW)
            view.data = uri
            startActivity(view)
        }

        var web = infl.findViewById<LinearLayout>(R.id.web)
        web.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity(Intent(context, WebView::class.java).putExtra("web", user.web))
            }
        })
        return infl
    }
}
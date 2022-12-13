package com.miu.cv

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.miu.cv.model.Contact
import com.miu.cv.model.Education
import com.miu.cv.model.Experience
import com.miu.cv.model.User
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {

    var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        fun createMe() {
            var contacts: Contact = Contact("bbatsukh@miu.edu", "(641) 819-2253", "https://github.com/subo8?tab=repositories", "https://www.instagram.com")
            var education: Education = Education("Bachelor of Computer Science", 2012, 2014, "CSMS")
            var education1: Education = Education("Master of Computer Science", 2021, 2024, "MIU")
            var experience: Experience = Experience(2018, 2021, "Full-Stack Developer", "TDBM")
            var experience1: Experience = Experience(2014, 2018, "DevOps Engineer", "TDBM")
            var experience2: Experience = Experience(2012, 2014, "Front-End Developer", "Khuur Group")
            users.add(User(R.drawable.male, "Byambadorj", "Batsukh", "Test123!",
                "Full-Stack Developer",
                "Software Engineer with over 5 years of hands-on experience includes software development, testing, security, implementing distributed, scalable web and mobile applications based on on-premises and cloud environments.",
                "https://byambadorj.me",
                "https://github.com/subo8",
                arrayListOf(education, education1), arrayListOf(experience, experience1, experience2), contacts))
        }
        //Create User
        createMe()

        val spf = getSharedPreferences("cv", Context.MODE_PRIVATE)

        if(spf.getBoolean("auth", false)) {
            val email = spf.getString("email", "")
            if (email !=null) {
                val found = findByEmail(email)
                if (found != null) {
                    val shoppingIntent = Intent(this,MainActivity::class.java )
                    shoppingIntent.putExtra("USER", found)
                    startActivity(shoppingIntent)
                }
            }
        }

        login.setOnClickListener {
            if (username.toString() != "" &&  password.text.toString() !="") {
                for (user in users) {
                    if(user.contacts.email == username.text.toString() && user.pswrd == password.text.toString()) {
                        val edit = spf.edit()
                        edit.putBoolean("auth", true)
                        edit.putString("email", username.text.toString())
                        edit.apply()

                        val found = findByEmail(username.text.toString())
                        if (found != null) {
                            val shoppingIntent = Intent(this,MainActivity::class.java )
                            shoppingIntent.putExtra("USER", found)
                            startActivity(shoppingIntent)
                        }
                    } else {
                        Toast.makeText(applicationContext, "Check credentials!!", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Inputs must not be null!", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun findByEmail (email: String): User?{
        for (user in users) {
            if (user.contacts.email == email) {
                return user
            }
        }
        return null
    }
}
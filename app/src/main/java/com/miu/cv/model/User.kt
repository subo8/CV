package com.miu.cv.model

import java.io.Serializable

class User (
    var image: Int,
    var fist_name: String,
    var last_name: String,
    var pswrd: String,
    var position: String,
    var about: String,
    var web: String,
    var github: String,
    var education: ArrayList<Education>,
    var roles: ArrayList<Experience>,
    var contacts: Contact,
        ): Serializable {
}
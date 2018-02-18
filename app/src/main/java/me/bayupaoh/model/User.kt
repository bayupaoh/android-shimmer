package me.bayupaoh.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by DODYDMW19 on 1/30/2018.
 */
open class User : RealmObject(){

    @PrimaryKey
    var id: Int? = 0
    var firstName: String? = ""
    var lastName: String? = ""
    var avatar: String? = ""

}

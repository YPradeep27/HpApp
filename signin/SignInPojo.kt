package com.hellopharmacy.views.activities.signin

class SignInPojo
{
    var status:Boolean= false
    var is_deactivated:Boolean= false
    var message: String?=null
    var data: Data?=null

class Data
{
    var id: String?=null
    var name: String?=null
    var email: String?=null
    var phone: String=""
    var image: String?=null
}
}
package com.hellopharmacy.viewmodels

import androidx.lifecycle.MutableLiveData
import com.hellopharmacy.models.SigninModel
import com.hellopharmacy.utilities.common.BaseViewModel
import com.hellopharmacy.views.activities.signin.SignInPojo
import com.hellopharmacy.utilities.common.pojo.ValidationsStatus
import javax.inject.Inject

/**
 * Created on ***  on 01/01/2020.
 */

class SigninViewModel
@Inject
constructor(public val signinModel: SigninModel) : BaseViewModel()
{

    var signinApiResult: MutableLiveData<SignInPojo> = MutableLiveData()
    var socialSigninApiResult: MutableLiveData<SignInPojo> = MutableLiveData()
    var validationResult : MutableLiveData<ValidationsStatus> = MutableLiveData()

    fun signin(emailOrPhone : String  ,password: String ,has_activate_account:String)
    {
        signinModel.signin(emailOrPhone ,  password,has_activate_account  ,signinApiResult ,loader)
    }
    fun socialSignin(social_id : String,  name : String,email:String , phone:String)
    {
        signinModel.socialSignin(social_id ,name, email ,  phone  ,signinApiResult ,loader)
    }

    fun validateSignin(emailOrPhone :String  ,  password: String)
    {
        signinModel.validateSignup(validationResult ,emailOrPhone , password  )
    }

}
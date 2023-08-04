package com.hellopharmacy.models

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.hellopharmacy.utilities.Constants
import com.hellopharmacy.utilities.daggerinjections.annotations.scopes.ApplicationScope
import com.hellopharmacy.utilities.retrofit.APIHelper
import com.hellopharmacy.utilities.common.pojo.ValidationsStatus
import com.hellopharmacy.utilities.retrofit.RetrofitService
import com.hellopharmacy.views.activities.signin.SignInPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created on *** on 02/01/2020.
 */

@ApplicationScope
class SigninModel

@Inject
constructor(private val retrofitService: RetrofitService)
{
    var status: ValidationsStatus = ValidationsStatus()

    @Inject
    lateinit var apiHelper: APIHelper


    fun signin(emailOrPhone : String, password : String,has_activate_account : String, data : MutableLiveData<SignInPojo>, loader : MutableLiveData<Boolean>): MutableLiveData<SignInPojo>
    {
            loader.value = true
            apiHelper.enqueueWithRetry(retrofitService.signIn(
                Constants.auth_key , emailOrPhone ,  password ,has_activate_account, Constants.DEVICE_ID , Constants.DEVICE_TYPE) ,Constants.RETRY_COUNT , object :
                Callback<SignInPojo>
            {
                override fun onResponse(call: Call<SignInPojo>, response: Response<SignInPojo>) {

                        loader.value = false
                        data.value = response.body()!!
                }
                override fun onFailure(call: Call<SignInPojo>, t: Throwable) {
                    loader.value = false
                    val mStatus  =
                        SignInPojo()
                    mStatus.status= false
                    mStatus.message = "Something went wrong! Please try again later."
                    data.value = mStatus
                }
            })
        return data
    }

    fun socialSignin(social_id : String, name : String, email:String, phone:String, data : MutableLiveData<SignInPojo>, loader : MutableLiveData<Boolean>): MutableLiveData<SignInPojo>
    {
            loader.value = true
            apiHelper.enqueueWithRetry(retrofitService.socialSignIn(
                Constants.auth_key , social_id ,  name , email,phone , Constants.DEVICE_ID , Constants.DEVICE_TYPE) ,Constants.RETRY_COUNT , object :
                Callback<SignInPojo>
            {
                override fun onResponse(call: Call<SignInPojo>, response: Response<SignInPojo>) {

                        loader.value = false
                        data.value = response.body()!!
                }
                override fun onFailure(call: Call<SignInPojo>, t: Throwable) {
                    loader.value = false
                    val mStatus  =
                        SignInPojo()
                    mStatus.status= false
                    mStatus.message = "Something went wrong! Please try again later."
                    data.value = mStatus
                }
            })
        return data
    }


     fun validateSignup(mStatus : MutableLiveData<ValidationsStatus>, emailOrPhone : String, password: String)
    {

        if (emailOrPhone.length == 0)
        {
            status.status = false
            status.message = "Please enter your email or phone number."
            mStatus.value = status
        }

        else if (emailOrPhone.contains("@"))
        {
            if(!Patterns.EMAIL_ADDRESS.matcher(emailOrPhone).matches()) {

                status.status = false
                status.message = "Please enter a valid email address."
                mStatus.value = status

            }
            else
            {
                if (password.length == 0)
                {
                    status.status = false
                    status.message = "Please enter your password."
                    mStatus.value = status
                }

                else if (password.length < 5)
                {
                    status.status = false
                    status.message = "Password should be at least 5 digits long."
                    mStatus.value = status

                }
                else
                {
                    status.status = true
                    status.message = "Success"
                    mStatus.value = status
                }
            }
        }

        else if (!Patterns.PHONE.matcher(emailOrPhone).matches())
        {

            status.status = false
            status.message = "Please enter a valid phone number."
            mStatus.value= status

        }

        else if (emailOrPhone.length<10 || emailOrPhone.length>13)
        {
            status.status = false
            status.message = "Please enter a valid phone number."
            mStatus.value= status
        }

        else if (password.length == 0)
        {
            status.status = false
            status.message = "Please enter your password."
            mStatus.value = status
        }

        else if (password.length < 5)
        {
            status.status = false
            status.message = "Password should be at least 5 digits long."
            mStatus.value = status

        }
        else
        {
            status.status =true
            status.message = ""
            mStatus.value = status
        }
    }


}
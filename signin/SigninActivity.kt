package com.hellopharmacy.views.activities.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hellopharmacy.R
import com.hellopharmacy.utilities.Constants
import com.hellopharmacy.utilities.common.BaseActivity
import com.hellopharmacy.viewmodels.SigninViewModel
import com.hellopharmacy.views.activities.WeblinksActivity
import com.hellopharmacy.views.activities.home.HomeActivity
import com.hellopharmacy.views.activities.signup.SignupActivity
import com.hellopharmacy.utilities.extensions.isNetworkActiveWithMessage
import com.hellopharmacy.views.activities.forgotpassword.ForgotPasswordActivity
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.progress_layout.*
import org.json.JSONException
import timber.log.Timber
import java.util.*


class SigninActivity : BaseActivity() , View.OnClickListener
{

    lateinit var mViewModel:SigninViewModel
    private val REQUEST_CODE_GOOGLE_SIGN_IN = 1 /* unique request id */
    var callbackManager: CallbackManager? = null
    private var loginManager: LoginManager? = null
    var has_activate_account = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        initializations()
        initAnimations()
        clickListeners()
        loadingObserver()
        validationObserver()
        apiObserver()
    }

    private fun initializations()
    {
        mViewModel = ViewModelProviders.of(this@SigninActivity , viewModelFactory).get(SigninViewModel::class.java)
        callbackManager = CallbackManager.Factory.create()
        facebookLogin()
    }

    private fun clickListeners()
    {
        signinSignupBtn.setOnClickListener(this)
        signinBtn.setOnClickListener(this)
        signinGmailBtn.setOnClickListener(this)
        signinFacebookBtn.setOnClickListener(this)
        signinTermsTxt.setOnClickListener(this)
        signinPrivacyTxt.setOnClickListener(this)
        signinForgotPwdBtn.setOnClickListener(this)
    }


    private fun loadingObserver()
    {

        mViewModel.loader.observe(this@SigninActivity , Observer {

            if(it)
            {
                aviProgressBar.visibility = View.VISIBLE
            }
            else
            {
                aviProgressBar.visibility = View.GONE
            }

        })
    }

    private fun validationObserver()
    {
        mViewModel.validationResult.observe(this@SigninActivity , Observer {

            if(it.status)
            {
                mViewModel.signin(signinusernameEdittxt.text.toString().trim() , signinPaaswordEdittxt.text.toString().trim() , has_activate_account)
            }
            else
            {
                showAlertMessage("Alert!" , it.message)
            }
        })
    }

    private fun apiObserver()
    {
        mViewModel.signinApiResult.observe(this@SigninActivity , Observer {

            if(it.status)
            {
                sharedPreference.saveString(Constants.User_id , it.data?.id.toString())
                sharedPreference.saveString(Constants.Name , it.data?.name.toString())
                sharedPreference.saveString(Constants.User_Email , it.data?.email.toString())
                sharedPreference.saveString(Constants.User_Phone ,it.data?.phone.toString())
                sharedPreference.saveString(Constants.Profile_Image ,it.data?.image.toString())

                var intent = Intent(this , HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            else {
                if(it.is_deactivated)
                    accountDeactivatedDialog()
                else
                showAlertMessage("Alert!", it.message.toString())
            }
        })


        mViewModel.socialSigninApiResult.observe(this@SigninActivity , Observer {

            if(it.status)
            {
                sharedPreference.saveString(Constants.User_id , it.data?.id.toString())
                sharedPreference.saveString(Constants.Name , it.data?.name.toString())
                sharedPreference.saveString(Constants.User_Email , it.data?.email.toString())
                sharedPreference.saveString(Constants.User_Phone ,it.data?.phone.toString())
                sharedPreference.saveString(Constants.Profile_Image ,it.data?.image.toString())

                var intent = Intent(this , HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            else
                showAlertMessage("Alert!" , it.message.toString())
        })
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.signinSignupBtn->
            {
                startActivity(Intent(this@SigninActivity , SignupActivity::class.java))
            }

            R.id.signinBtn->
            {

                              if(isNetworkActiveWithMessage())
                              {
                                  mViewModel.validateSignin(signinusernameEdittxt.text.toString().trim() , signinPaaswordEdittxt.text.toString().trim())
                              }
            }

            R.id.signinTermsTxt->
            {
                if(isNetworkActiveWithMessage()) {
                    var bundle = Bundle()
                    bundle.putString("type", "terms")
                    startActivity(Intent(this, WeblinksActivity::class.java).putExtras(bundle))
                }

            }
            R.id.signinPrivacyTxt->
            {
                if(isNetworkActiveWithMessage())
                {
                    var bundle = Bundle()
                    bundle.putString("type" , "privacy")
                    startActivity(Intent(this , WeblinksActivity::class.java).putExtras(bundle))

                }
            }
            R.id.signinForgotPwdBtn->
            {
                    startActivity(Intent(this , ForgotPasswordActivity::class.java))
            }
            R.id.signinGmailBtn->
            {
                if(isNetworkActiveWithMessage())
                    googleSignin()
            }

            R.id.signinFacebookBtn->
            {
                if(isNetworkActiveWithMessage())
                loginManager?.logInWithReadPermissions(
                    this@SigninActivity,
                    Arrays.asList(
                        "email"))
            }
        }
    }


    private fun accountDeactivatedDialog()
    {
        val mDialog = Dialog(this, R.style.NewDialogTheme)
        mDialog.setCancelable(true)
        mDialog.setCanceledOnTouchOutside(true)
        mDialog.setContentView(R.layout.accout_deactivate_dialog_layout)
        mDialog.show()

        val yesBtn = mDialog.findViewById<Button>(R.id.activate_yes_btn)
        val noBtn = mDialog.findViewById<Button>(R.id.activate_no_btn)

        noBtn.setOnClickListener {
            mDialog.dismiss()
        }

        yesBtn.setOnClickListener {
            if(isNetworkActiveWithMessage())
            {
                has_activate_account= "true"
                mViewModel.validateSignin(signinusernameEdittxt.text.toString().trim() , signinPaaswordEdittxt.text.toString().trim())
            }

        }
    }
    fun googleSignin()
    {
        val request = GetSignInIntentRequest.builder()
            .setServerClientId(getString(R.string.server_client_id))
            .build()

        Identity.getSignInClient(this@SigninActivity)
            .getSignInIntent(request)
            .addOnSuccessListener {
                startIntentSenderForResult(
                    it.intentSender,
                    REQUEST_CODE_GOOGLE_SIGN_IN,
                    /* fillInIntent= */ null,
                    /* flagsMask= */ 0,
                    /* flagsValue= */ 0,
                    /* extraFlags= */ 0,
                    /* options= */ null)

            }.addOnFailureListener {
                Toast.makeText(this@SigninActivity , "Failed to login with Google" , Toast.LENGTH_SHORT).show()
            }

    }

    fun facebookLogin() {
        loginManager = LoginManager.getInstance()
        callbackManager = CallbackManager.Factory.create()
        loginManager?.registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        val request = GraphRequest.newMeRequest(
                            loginResult.accessToken
                        ) { `object`, response ->
                            if (`object` != null) {
                                try {
                                    val name = `object`.getString("name")
                                    val email = `object`.getString("email")
                                    val fbUserID = `object`.getString("id")


                                    mViewModel.socialSignin(fbUserID , name , email , "")
                                    //Toast.makeText(this@SigninActivity , name , Toast.LENGTH_SHORT).show()

                                    disconnectFromFacebook()

                                    // do action after Facebook login success
                                    // or call your API
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                } catch (e: NullPointerException) {
                                    e.printStackTrace()
                                }
                            }
                        }
                        val parameters = Bundle()
                        parameters.putString(
                            "fields",
                            "id, name, email, gender, birthday"
                        )
                        request.parameters = parameters
                        request.executeAsync()
                    }

                    override fun onCancel() {
                        Log.v("LoginScreen", "---onCancel")
                    }

                    override fun onError(error: FacebookException) {
                        // here write code when get error
                        Log.v(
                            "LoginScreen", "----onError: "
                                    + error.message
                        )
                    }
                })
    }

    fun disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return  // already logged out
        }
        GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/me/permissions/",
            null,
            HttpMethod.DELETE,
            GraphRequest.Callback { LoginManager.getInstance().logOut() })
            .executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {

        callbackManager?.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
            try {
                val credential: SignInCredential =
                    Identity.getSignInClient(this@SigninActivity).getSignInCredentialFromIntent(data)
                // Signed in successfully - show authenticated UI
               Timber.e(credential.displayName)
               Timber.e(credential.googleIdToken)
               Timber.e(credential.id)

                mViewModel.socialSignin(credential.googleIdToken.toString() , credential.displayName.toString() , credential.id.toString()  , "")
                //Toast.makeText(this@SigninActivity , credential.displayName , Toast.LENGTH_SHORT).show()
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
            }
        }
    }
    private fun initAnimations()
    {
        signinLogoImg.visibility = View.INVISIBLE
        signinTxt.visibility = View.INVISIBLE
        signinUsernameLayout.visibility = View.INVISIBLE
        signinPasswordLayout.visibility = View.INVISIBLE
        signinBtn.visibility = View.INVISIBLE
        signinOrTxt.visibility = View.INVISIBLE
        signinGmailBtn.visibility = View.INVISIBLE
        signinFacebookBtn.visibility = View.INVISIBLE
        signinTermsPrivacyLayout.visibility = View.INVISIBLE
        signinForgotPwdBtn.visibility = View.INVISIBLE

        YoYo.with(Techniques.SlideInUp)
            .duration(3)
            .onEnd {

                signinLogoImg.visibility = View.VISIBLE
                YoYo.with(Techniques.FadeInDown)
                    .duration(400)
                    .onEnd {

                        signinTxt.visibility = View.VISIBLE
                        YoYo.with(Techniques.FadeInDown)
                            .duration(300)
                            .onEnd {

                                signinUsernameLayout.visibility = View.VISIBLE
                                YoYo.with(Techniques.FadeInLeft)
                                    .duration(380)
                                    .onEnd {

                                    }
                                    .playOn(signinUsernameLayout)

                                signinPasswordLayout.visibility = View.INVISIBLE
                                YoYo.with(Techniques.FadeInLeft)
                                    .duration(200)
                                    .onEnd {

                                        signinPasswordLayout.visibility = View.VISIBLE
                                        YoYo.with(Techniques.FadeInLeft)
                                            .duration(380)
                                            .onEnd {

                                            }
                                            .playOn(signinPasswordLayout)

                                        signinBtn.visibility = View.INVISIBLE
                                        YoYo.with(Techniques.FadeInLeft)
                                            .duration(200)
                                            .onEnd {

                                                signinBtn.visibility = View.VISIBLE
                                                YoYo.with(Techniques.FadeInLeft)
                                                    .duration(380)
                                                    .onEnd {
                                                        signinOrTxt.visibility = View.VISIBLE
                                                        YoYo.with(Techniques.FadeIn)
                                                            .duration(400)
                                                            .onEnd {

                                                                signinGmailBtn.visibility = View.VISIBLE
                                                                YoYo.with(Techniques.FadeInLeft)
                                                                    .duration(400)
                                                                    .onEnd {

                                                                    }
                                                                    .playOn(signinGmailBtn)


                                                                signinFacebookBtn.visibility = View.VISIBLE
                                                                YoYo.with(Techniques.FadeInRight)
                                                                    .duration(400)
                                                                    .onEnd {

                                                                    }
                                                                    .playOn(signinFacebookBtn)

                                                                signinTermsPrivacyLayout.visibility = View.VISIBLE
                                                                YoYo.with(Techniques.FadeInUp)
                                                                    .duration(400)
                                                                    .onEnd {

                                                                    }
                                                                    .playOn(signinTermsPrivacyLayout)

                                                            }

                                                            .playOn(signinOrTxt)


                                                        signinForgotPwdBtn.visibility = View.VISIBLE
                                                        YoYo.with(Techniques.FadeIn)
                                                            .duration(400)
                                                            .onEnd {

                                                            }
                                                            .playOn(signinForgotPwdBtn)
                                                    }
                                                    .playOn(signinBtn)
                                            }
                                            .playOn(signinBtn)

                                    }
                                    .playOn(signinPasswordLayout)

                            }
                            .playOn(signinTxt)
                    }
                    .playOn(signinLogoImg)

            }
            .playOn(signinLogoImg)

    }
}
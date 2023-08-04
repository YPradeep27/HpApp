package com.hellopharmacy.utilities.common


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hellopharmacy.R
import com.hellopharmacy.utilities.MyApplication
import com.hellopharmacy.utilities.daggerinjections.modules.HelperSharedPreferences
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
open class BaseFragment : Fragment()
{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: HelperSharedPreferences


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //(activity?.application as MyApplication).myComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as MyApplication).myComponent.inject(this)
    }

    fun showSnackbar(message: String)
    {
        try {
            val parentLayout = activity?.findViewById<View>(android.R.id.content)
            var sbView: Snackbar
            sbView = Snackbar.make(parentLayout!!, message, Snackbar.LENGTH_SHORT)
            sbView.setBackgroundTint(ContextCompat.getColor(activity as Activity, R.color.colorPrimary))
            sbView.show()
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }
}


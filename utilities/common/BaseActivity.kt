package com.hellopharmacy.utilities.common

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.hellopharmacy.R
import com.hellopharmacy.utilities.MyApplication
import com.hellopharmacy.utilities.customdialogs.CustomDialog
import com.hellopharmacy.utilities.customdialogs.DialogListenerInterface
import com.hellopharmacy.utilities.daggerinjections.modules.HelperSharedPreferences
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@BindingAdapter("formatDate")
fun simpledateFormat(view : TextView? , date: String)
{
    try
    {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val datetime = dateFormat.parse(date)//You will get date object relative to server/client timezone wherever it is parsed
        var dateStr = ""

        val calendar = Calendar.getInstance()
        calendar.time = datetime
        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)
        val timeFormatter = SimpleDateFormat("hh:mm a")

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(
                        Calendar.DAY_OF_YEAR
                ))
        {
            //dateStr = "Today " + timeFormatter.format(datetime)

            val now = Date()
            val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(now.time - datetime.time)
            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(now.time - datetime.time)
            val hours: Long = TimeUnit.MILLISECONDS.toHours(now.time - datetime.time)
            val days: Long = TimeUnit.MILLISECONDS.toDays(now.time - datetime.time)

            if(seconds<60)
            {
                dateStr  = ""+ seconds +" secs"
            }
            else if(minutes<60)
            {
                dateStr = ""+ minutes+" mins"
            }
            else if(hours<24)
            {
                dateStr = ""+hours+" hours"
            }
/*            else
            {
                dateStr =  ""+days+" days"
            }*/
        }

        else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(
                        Calendar.DAY_OF_YEAR
                ) == yesterday.get(Calendar.DAY_OF_YEAR))
        {
            dateStr = "Yesterday " + timeFormatter.format(datetime)
        }
        else
        {

            val now = Date()
            val days: Long = TimeUnit.MILLISECONDS.toDays(now.time - datetime.time)
            dateStr =  ""+days+" days"
            //dateStr = formatter.format(datetime)
        }
        view?.text = dateStr
    }

    catch (e: Exception)
    {
        e.printStackTrace()
        view?.text = date
    }

}

@BindingAdapter("formatTrackOrderDate")
fun formatTrackOrderDate(view : TextView? , date: String)
{
    try
    {


            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(date)
            val dateFormatter = SimpleDateFormat("dd MMM, yyyy hh:mm a") //this format changeable
            dateFormatter.timeZone = TimeZone.getDefault()
           val  mDate = dateFormatter.format(value)

            //Log.d("ourDate", ourDate);

        view?.text = mDate

     /*
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        Log.e("Timezone+ " ,""+TimeZone.getDefault().id)
        Log.e("Timezone+ " ,""+TimeZone.getDefault().displayName)
        Log.e("Timezone+ " ,""+TimeZone.getDefault())

        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        dateFormat.timeZone = timeZone

        val datetime = dateFormat.parse(date)//You will get date object relative to server/client timezone wherever it is parsed
        var dateStr = ""

        val timeFormatter = SimpleDateFormat("dd MMM, yyyy hh:mm a")

            dateStr = timeFormatter.format(datetime)

        view?.text = dateStr*/
    }

    catch (e: Exception)
    {
        e.printStackTrace()
        view?.text = date
    }

}


@BindingAdapter("formatOrderDate")
fun dateFormat(view : TextView? ,date: String)
{
    try
    {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val datetime = dateFormat.parse(date)//You will get date object relative to server/client timezone wherever it is parsed
        var dateStr = ""
         val mformatter = SimpleDateFormat("dd MMM yyyy")
         dateStr = mformatter.format(datetime)

        view?.text =  dateStr
    }

    catch (e: Exception)
    {
        e.printStackTrace()

    }

}


@BindingAdapter("loadImage")
fun loadImage(view: ImageView?, imageUrl: String?)
{
    view?.let {
        Glide.with(view.context)
            .load(imageUrl)
            .thumbnail(.2f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //.placeholder(R.color.light_grey1)
            .into(it)
    }
}

open class BaseActivity : AppCompatActivity()
{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreference: HelperSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE)
        }

        (application as MyApplication).myComponent.inject(this)

    }

    fun showAlertMessage(title:String , message: String)
    {
        try
        {

            val mDialog = CustomDialog(this)
            mDialog.setTitle(title)
            mDialog.setMessage(message)
            mDialog.setPositiveButton("Ok" , object :
                DialogListenerInterface.onPositiveClickListener {
                override fun onPositiveClick() {

                }
            })
            mDialog.show()

        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }


    fun showSnackbar(message: String)
    {
        try {
            val parentLayout = findViewById<View>(android.R.id.content)
            var sbView: Snackbar
            sbView = Snackbar.make(parentLayout!!, message, Snackbar.LENGTH_SHORT)
            sbView.setBackgroundTint(ContextCompat.getColor( this, R.color.colorPrimary))
            sbView.show()
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

    fun changeStatusBarColor()
    {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0)
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(resources.getColor(R.color.appColor))
        }

    }
}
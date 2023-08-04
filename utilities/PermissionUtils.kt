package com.hellopharmacy.utilities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hellopharmacy.R
import permissions.dispatcher.PermissionRequest

object PermissionUtils
{
    fun showRationalDialog(context: Context, @StringRes messageResId: Int, request: PermissionRequest) {
        AlertDialog.Builder(context)
            .setPositiveButton(R.string.permission_btn_allow) { _, _ -> request.proceed() }
            .setNegativeButton(R.string.permission_btn_deny) { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(messageResId)
            .show()
    }

    fun showAppSettingsDialog(activity: AppCompatActivity, @StringRes messageResId: Int, requestCode: Int) {
        AlertDialog.Builder(activity)
            .setPositiveButton(R.string.permission_label_settings) { _, _ ->
                activity.startActivityForResult(getAppSettingsIntent(activity), requestCode)
            }
            .setNegativeButton(R.string.permission_btn_cancel) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .setMessage(messageResId)
            .show()
    }

    fun showAppSettingsDialog(fragment: Fragment, @StringRes messageResId: Int, requestCode: Int) {
        val context = fragment.activity
        context ?: return

        AlertDialog.Builder(context)
            .setPositiveButton(R.string.permission_label_settings) { _, _ ->
                fragment.startActivityForResult(getAppSettingsIntent(context), requestCode)
            }
            .setNegativeButton(R.string.permission_btn_cancel) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .setMessage(messageResId)
            .show()
    }

    private fun getAppSettingsIntent(context: Context): Intent {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", context.packageName, null)
        return intent
    }

    /**
     * Function to request permission from the user
     */
    fun requestAccessFineLocationPermission(activity: AppCompatActivity, requestId: Int) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION),
            requestId
        )
    }

    /**
     * Function to check if the location permissions are granted or not
     */
    fun isAccessFineLocationGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Function to check if location of the device is enabled or not
     */
    fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    /**
     * Function to show the "enable GPS" Dialog box
     */
    fun showGPSNotEnabledDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.enable_gps))
            .setMessage(context.getString(R.string.required_for_this_app))
            .setCancelable(false)
            .setPositiveButton(context.getString(R.string.enable_now)) { _, _ ->
                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .show()
    }
}
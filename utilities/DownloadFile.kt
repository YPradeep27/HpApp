package com.hellopharmacy.utilities

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.hellopharmacy.R
import timber.log.Timber
import java.io.File

class DownloadFile
{
    var downloadReference: Long = 0

    fun DownloadData(uri: Uri?, mContext: Context): Long {

        Timber.e(uri?.path)
        // Create request for android download manager
        val downloadManager =
            mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Setting title of request
        request.setTitle(uri?.path.toString().split("/").get(uri?.path.toString().split("/").size-1))

        request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, File.separator + mContext.resources.getString(R.string.app_name) + File.separator +  uri?.path.toString().split("/").get(uri?.path.toString().split("/").size-1))

        //Setting description of request
        //request.setDescription("Please wait");

     //   val date= java.util.Date()
       // val timeStamp =  SimpleDateFormat("yyyyMMdd_HHmmss").format(date.getTime());

//Set the local destination for the downloaded file to a path within the application's external files directory
       // request.setDestinationInExternalFilesDir(mContext, Environment.getExternalStoragePublicDirectory(
         //   Environment.DIRECTORY_MOVIES).toString() , mContext.resources.getString(R.string.app_name)+"/"+uri?.path.toString().split("/").get(uri?.path.toString().split("/").size-1))

/*        // Check that the SDCard is mounted
        var mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), mContext.resources.getString(R.string.app_name));

        // Create the storage directory(MyCameraVideo) if it does not exist
        if (! mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {

            }
        }*/
           //     request.setDestinationInExternalPublicDir(String.format("%s/%s", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
            //mContext.resources.getString(R.string.app_name)), uri?.path.toString().split("/")[uri?.path.toString().split("/").size-1]
       // )
        /*Log.i("myi", "downloadImage: " + request.setDestinationInExternalPublicDir(String.format("%s/%s", Environment.getExternalStorageDirectory(),
            getString(R.string.app_name)), "FileName.jpg"));*/

        //Enqueue download and save into referenceId
        downloadReference = downloadManager.enqueue(request)
        return downloadReference
    }
}
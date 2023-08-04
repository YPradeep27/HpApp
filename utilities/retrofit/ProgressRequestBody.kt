package com.hellopharmacy.utilities.retrofit

import android.os.Handler
import android.os.Looper
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created on *** on 20/10/2019.
 */
class ProgressRequestBody(private val mFile: File, private val mListener: UploadCallbacks, type: String) : RequestBody()
{
    private val mPath: String? = null
    private var type = ""

    interface UploadCallbacks {
        fun onProgressUpdate(percentage: Int)
        fun onError()
        fun onFinish()
    }

    override fun contentType(): MediaType? {
        return MediaType.parse(type)
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return mFile.length()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val fileLength = mFile.length()
        val buffer =
            ByteArray(DEFAULT_BUFFER_SIZE)
        val `in` = FileInputStream(mFile)
        var uploaded: Long = 0
        try {
            var read: Int
            val handler = Handler(Looper.getMainLooper())
            while (`in`.read(buffer).also { read = it } != -1) { // update progress on UI thread
                uploaded += read.toLong()
                handler.post(ProgressUpdater(uploaded, fileLength))
                sink.write(buffer, 0, read)
            }
        } finally {
            `in`.close()
        }
    }

    private inner class ProgressUpdater(private val mUploaded: Long, private val mTotal: Long) :
        Runnable {
        override fun run() {
            mListener.onProgressUpdate((100 * mUploaded / mTotal).toInt())
            //mListener.onFinish();
        }

    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 4096
    }

    init {
        this.type = type
    }
}
package com.hellopharmacy.utilities.extensions

import android.content.Intent

fun Intent.clearStack() {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
}

package com.sudzbubblez.utilities.common.pojo

/**
 * Created on *** on 18/02/2020.
 */
class ApiStatus(message: String = "", code: String = "", status: Boolean = false)
{
    var status:Boolean = status
    var message: String = message
    var code: String = code
}
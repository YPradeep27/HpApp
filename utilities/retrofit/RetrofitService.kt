package com.hellopharmacy.utilities.retrofit

import com.giftawish.views.activities.changepassword.ChangePasswordPojo
import com.hellopharmacy.views.activities.appointment.BookAppointmentPojo
import com.hellopharmacy.views.activities.appointmentfilter.CityPojo
import com.hellopharmacy.views.activities.appointmentfilter.CountryPojo
import com.hellopharmacy.views.activities.appointmentfilter.StatePojo
import com.hellopharmacy.views.activities.appointmentratings.AddReviewRatingPojo
import com.hellopharmacy.views.activities.appointmentratings.AppointmentRatingsPojo
import com.hellopharmacy.views.activities.beautyskincare.BeautyCarePojo
import com.hellopharmacy.views.activities.prescriptiondetails.CheckPrescriptionPojo
import com.hellopharmacy.views.activities.forgotpassword.ForgotPasswordPojo
import com.hellopharmacy.views.activities.signin.SignInPojo
import com.hellopharmacy.views.activities.signup.SignUpPojo
import com.hellopharmacy.views.activities.availablestores.AvailableStoresPojo
import com.hellopharmacy.views.activities.availablestoresdetails.AvailableStoresDetailsPojo
import com.hellopharmacy.views.activities.cart.CartPojo
import com.hellopharmacy.views.activities.cart.SimilarProductsPojo
import com.hellopharmacy.views.activities.coupons.CouponsCategoryPojo
import com.hellopharmacy.views.activities.coupons.CouponsPojo
import com.hellopharmacy.views.activities.editprofile.EditProfilePojo
import com.hellopharmacy.views.activities.loyalitypoints.LoyaltyPointsPojo
import com.hellopharmacy.views.activities.membership.MembershipPojo
import com.hellopharmacy.views.activities.orderdetails.TrackOrderPojo
import com.hellopharmacy.views.activities.productdetails.ProductDetailsPojo
import com.hellopharmacy.views.activities.searchproducts.AddToCartPojo
import com.hellopharmacy.views.activities.searchproducts.SearchProductsHintPojo
import com.hellopharmacy.views.activities.searchproducts.SearchProductsPojo
import com.hellopharmacy.views.activities.uploadprescription.DeletePrescriptionPojo
import com.hellopharmacy.views.activities.uploadprescription.MyPrescriptionPojo
import com.hellopharmacy.views.activities.uploadprescription.UploadPrescriptionPojo
import com.hellopharmacy.views.fragments.appointment.AppointmentDetailsPojo
import com.hellopharmacy.views.fragments.appointment.CancelAppointmentPojo
import com.hellopharmacy.views.fragments.appointment.adapters.AppointmentHistoryPojo
import com.hellopharmacy.views.fragments.appointment.adapters.UpcomingAppointmentPojo
import com.hellopharmacy.views.fragments.confirmorder.OrderPlacedPojo
import com.hellopharmacy.views.fragments.home.AdsPojo
import com.hellopharmacy.views.fragments.orderhistory.OrderHistoryPojo
import com.hellopharmacy.views.fragments.profile.MyProfilePojo
import com.hellopharmacy.views.fragments.shippingaddress.ShippingAddressPojo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created on *** on 01/02/2021.
 */
interface RetrofitService
{
    @FormUrlEncoded
    @POST("sign-up")
    fun signUp(@Header("auth_key") auth_key: String
                       , @Field("name") name: String
                       , @Field("email") email: String
                       , @Field("phone") phone: String
                       , @Field("password") password: String
                       , @Field("device_id") device_id: String
                       , @Field("device_type") device_type: String): Call<SignUpPojo>

    @FormUrlEncoded
    @POST("sign-in")
    fun signIn(@Header("auth_key") auth_key: String
               , @Field("username") email: String
               , @Field("password") password: String
               , @Field("activate_account") hasactivate_account_activate_account: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<SignInPojo>

    @FormUrlEncoded
    @POST("social-sign-in")
    fun socialSignIn(@Header("auth_key") auth_key: String
               , @Field("social_id") social_id: String
               , @Field("name") name: String
               , @Field("email") email: String
               , @Field("phone") phone: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<SignInPojo>

    @FormUrlEncoded
    @POST("screen_ads")
    fun getAds(@Header("auth_key") auth_key: String
               , @Field("screen_name") email: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<AdsPojo>


    @Multipart
    @POST("upload-prescription")
    fun updatePrescriptionApi(@Part image: MultipartBody.Part?
                                 , @Part("auth_key") auth_key: RequestBody
                                 , @Part("user_id") user_id: RequestBody
    ): Call<UploadPrescriptionPojo>


    @FormUrlEncoded
    @POST("get_prescription_list")
    fun getMyPrescription(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<MyPrescriptionPojo>

    @FormUrlEncoded
    @POST("deletePrescription")
    fun deleteOrCancelMyPrescription(@Header("auth_key") auth_key: String
                                     , @Field("user_id") user_id: String
                                     , @Field("prescription_id") prescription_id: String
                                     , @Field("type") String: String
                                     , @Field("device_id") device_id: String
                                     , @Field("device_type") device_type: String): Call<DeletePrescriptionPojo>


    @FormUrlEncoded
    @POST("refillPrescription")
    fun refillMyPrescription(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("prescription_id") prescription_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<DeletePrescriptionPojo>


    @FormUrlEncoded
    @POST("get-stores")
    fun getAvailableStores(@Header("auth_key") auth_key: String
                           , @Field("user_id") email: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<AvailableStoresPojo>


    @FormUrlEncoded
    @POST("search-prescription")
    fun checkPrescriptionStatus(@Header("auth_key") auth_key: String
                           , @Field("user_id") email: String
                           , @Field("prescription_code") prescription_code: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<CheckPrescriptionPojo>

    @FormUrlEncoded
    @POST("get_subcat_stores")
    fun getBeautyStoreList(@Header("auth_key") auth_key: String
               , @Field("sub_catId") category_id: String
               , @Field("latitude") latitute: String
               , @Field("longitude") longitute: String
               , @Field("distance") distance: String
               , @Field("rating") rating: String
               , @Field("country") country: String
               , @Field("state") state: String
               , @Field("city") city: String
               , @Field("zipcode") zipcode: String
               , @Field("landmark") landmark: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<BeautyCarePojo>

    @FormUrlEncoded
    @POST("forget_password")
    fun forgotPassword(@Header("auth_key") auth_key: String
               , @Field("input_text") input_text: String
               , @Field("type") type: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<ForgotPasswordPojo>

    @FormUrlEncoded
    @POST("reset_password")
    fun resetPassword(@Header("auth_key") auth_key: String
               , @Field("user_id") user_id: String
               , @Field("password") password: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<SignInPojo>


    @FormUrlEncoded
    @POST("get_cat_store_detail")
    fun getAvailableStoreDetails(@Header("auth_key") auth_key: String
                                , @Field("store_id") email: String
                                , @Field("device_id") device_id: String
                                , @Field("device_type") device_type: String): Call<AvailableStoresDetailsPojo>

    @FormUrlEncoded
    @POST("getCategories")
    fun getCouponCategories(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<CouponsCategoryPojo>

    @FormUrlEncoded
    @POST("getCoupons")
    fun getCoupons(@Header("auth_key") auth_key: String
                   , @Field("user_id") user_id: String
                   , @Field("category_id") category_id: String
                   , @Field("device_id") device_id: String
                   , @Field("device_type") device_type: String): Call<CouponsPojo>

    @FormUrlEncoded
    @POST("searchProducts")
    fun searchProducts(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("search") search: String
                           , @Field("type") type: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<SearchProductsPojo>

    @FormUrlEncoded
    @POST("searchCategories")
    fun searchProductSuggestions(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("search") search: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<SearchProductsHintPojo>

    @FormUrlEncoded
    @POST("addTocart")
    fun addToCart(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("product_id") product_id: String
                           , @Field("quantity") quantity: String
                           , @Field("size") size: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<AddToCartPojo>

    @FormUrlEncoded
    @POST("moveToCart")
    fun moveToCart(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("product_id") product_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<AddToCartPojo>

    @FormUrlEncoded
    @POST("productDetail")
    fun getProductDetails(@Header("auth_key") auth_key: String
                                 , @Field("product_id") product_id: String
                                 , @Field("user_id") user_id: String
                                 , @Field("device_id") device_id: String
                                 , @Field("device_type") device_type: String): Call<ProductDetailsPojo>

    @FormUrlEncoded
    @POST("deleteItem")
    fun deleteProductFromCart(@Header("auth_key") auth_key: String
                  , @Field("user_id") user_id: String
                  , @Field("product_id") product_id: String
                  , @Field("device_id") device_id: String
                  , @Field("device_type") device_type: String): Call<AddToCartPojo>

    @FormUrlEncoded
    @POST("saveLater")
    fun saveProductForLater(@Header("auth_key") auth_key: String
                  , @Field("user_id") user_id: String
                  , @Field("product_id") product_id: String
                  , @Field("device_id") device_id: String
                  , @Field("device_type") device_type: String): Call<AddToCartPojo>

    @FormUrlEncoded
    @POST("getCart")
    fun getCart(@Header("auth_key") auth_key: String
                       , @Field("user_id") user_id: String
                       , @Field("device_id") device_id: String
                       , @Field("device_type") device_type: String): Call<CartPojo>

    @FormUrlEncoded
    @POST("similarProducts")
    fun getSimilarProducts(@Header("auth_key") auth_key: String
                       , @Field("user_id") user_id: String
                       , @Field("device_id") device_id: String
                       , @Field("device_type") device_type: String): Call<SimilarProductsPojo>

    @FormUrlEncoded
    @POST("addAddress")
    fun addEditAddress(@Header("auth_key") auth_key: String
               , @Field("user_id") user_id: String
               , @Field("address_id") address_id: String
               , @Field("user_name") user_name: String
               , @Field("flat_no") flat_no: String
               , @Field("state") state: String
               , @Field("city") city: String
               , @Field("zipcode") zipcode: String
               , @Field("country") country: String
               , @Field("phone") phone: String
               , @Field("area") area: String
               , @Field("landmark") landmark: String
               , @Field("address_type") address_type: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<SignInPojo>

    @FormUrlEncoded
    @POST("getAddress")
    fun getAddresses(@Header("auth_key") auth_key: String
               , @Field("user_id") user_id: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<ShippingAddressPojo>

    @FormUrlEncoded
    @POST("deleteAddress")
    fun deleteAddresses(@Header("auth_key") auth_key: String
               , @Field("user_id") user_id: String
               , @Field("address_id") address_id: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<ShippingAddressPojo>

    @FormUrlEncoded
    @POST("updateCart")
    fun updateCart(@Header("auth_key") auth_key: String
                  , @Field("user_id") user_id: String
                  , @Field("cart_id") cart_id: String
                  , @Field("quantity") quantity: String
                  , @Field("device_id") device_id: String
                  , @Field("device_type") device_type: String): Call<AddToCartPojo>


    @FormUrlEncoded
    @POST("placeOrder")
    fun placeOrder(@Header("auth_key") auth_key: String
                     , @Field("user_id") user_id: String
                     , @Field("addressId") addressId: String
                     , @Field("type") type: String
                     , @Field("totalAmount") totalAmount: String
                     , @Field("device_id") device_id: String
                     , @Field("device_type") device_type: String): Call<OrderPlacedPojo>

    @FormUrlEncoded
    @POST("orderPrescription")
    fun placePrescriptionOrder(@Header("auth_key") auth_key: String
                   , @Field("user_id") user_id: String
                   , @Field("prescription_id") prescription_id: String
                   , @Field("address") addressId: String
                   , @Field("phone") type: String
                   , @Field("total") totalAmount: String
                   , @Field("device_id") device_id: String
                   , @Field("device_type") device_type: String): Call<OrderPlacedPojo>


    @FormUrlEncoded
    @POST("appointmentReview")
    fun addReviewRating(@Header("auth_key") auth_key: String
               , @Field("user_id") user_id: String
                        , @Field("id") id: String
               , @Field("rating") rating: String
               , @Field("review") review: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<AddReviewRatingPojo>


    @FormUrlEncoded
    @POST("getappointmentReview")
    fun getAppointmentRatingReview(@Header("auth_key") auth_key: String
                     , @Field("user_id") user_id: String
                     , @Field("id") id: String
                     , @Field("device_id") device_id: String
                     , @Field("device_type") device_type: String): Call<AppointmentRatingsPojo>

    @FormUrlEncoded
    @POST("getSubCategories")
    fun getProductSubCategories(@Header("auth_key") auth_key: String
                            , @Field("user_id") user_id: String
                            , @Field("category_id") category_id: String
                            , @Field("device_id") device_id: String
                            , @Field("device_type") device_type: String): Call<CouponsCategoryPojo>

    @GET("getCountries")
    fun getCountries(@Header("auth_key") auth_key: String): Call<CountryPojo>

    @FormUrlEncoded
    @POST("getStates")
    fun getStatesByCountry(@Header("auth_key") auth_key: String
                                , @Field("country") country: String
                                , @Field("device_id") device_id: String
                                , @Field("device_type") device_type: String): Call<StatePojo>

    @FormUrlEncoded
    @POST("getCities")
    fun getCitiesByStates(@Header("auth_key") auth_key: String
                                , @Field("state") state: String
                                , @Field("device_id") device_id: String
                                , @Field("device_type") device_type: String): Call<CityPojo>

    @FormUrlEncoded
    @POST("getHistoryOrCancelledAppointments")//appointment_type 1 for history and 2 for cancelled appointments
    fun getHistoryOrCancelledAppointments(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("appointment_type") appointment_type: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<AppointmentHistoryPojo>

    @FormUrlEncoded
    @POST("getUpcomingAppointments")
    fun getUpcomingAppointment(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<UpcomingAppointmentPojo>


    @FormUrlEncoded
    @POST("appointmentDetails")
    fun getAppointmentDetails(@Header("auth_key") auth_key: String
                           , @Field("appointment_id") appointment_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<AppointmentDetailsPojo>

    @FormUrlEncoded
    @POST("cancelAppointment")
    fun cancelAppointment(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("appointment_id") appointment_id: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<CancelAppointmentPojo>


    @FormUrlEncoded
    @POST("serviceDetails")
    fun getServiceDetails(@Header("auth_key") auth_key: String
               , @Field("id") id: String
               , @Field("date") date: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<BookAppointmentPojo>

    @FormUrlEncoded
    @POST("bookAppointment")
    fun bookAppointment(@Header("auth_key") auth_key: String
               , @Field("user_id") user_id: String
               , @Field("id") id: String
               , @Field("start_time") start_time: String
               , @Field("end_time") end_time: String
               , @Field("payment") payment: String
               , @Field("tax") tax: String
               , @Field("total") total: String
               , @Field("has_qurantine") has_qurantine: String
               , @Field("is_new_customer") is_new_customer: String
               , @Field("date") date: String
               , @Field("phone_number") phone_number: String
               , @Field("note") note: String
               , @Field("device_id") device_id: String
               , @Field("device_type") device_type: String): Call<BookAppointmentPojo>


    @FormUrlEncoded
    @POST("myOrders")
    fun getMyOrders(@Header("auth_key") auth_key: String
                   , @Field("user_id") user_id: String
                   , @Field("device_id") device_id: String
                   , @Field("device_type") device_type: String): Call<OrderHistoryPojo>


    @Multipart
    @POST("editProfile")
    fun updateProfileApi(@Part image: MultipartBody.Part?
                              , @Part("auth_key") auth_key: RequestBody
                              , @Part("user_id") user_id: RequestBody
                              , @Part("name") name: RequestBody
                              , @Part("phone_no") phone_no: RequestBody
                              , @Part("address") address: RequestBody
    ): Call<EditProfilePojo>

    @FormUrlEncoded
    @POST("getProfile")
    fun getMyProfileApi(@Header("auth_key") auth_key: String
                    , @Field("user_id") user_id: String
                    , @Field("device_id") device_id: String
                    , @Field("device_type") device_type: String): Call<MyProfilePojo>


    @FormUrlEncoded
    @POST("change-password")
    fun changePassword(@Header("auth_key") auth_key: String
                           , @Field("user_id") user_id: String
                           , @Field("old_password") old_password: String
                           , @Field("new_password") new_password: String
                           , @Field("device_id") device_id: String
                           , @Field("device_type") device_type: String): Call<ChangePasswordPojo>

    @FormUrlEncoded
    @POST("deleteSavedItem")
    fun deleteProductFromSaved(@Header("auth_key") auth_key: String
                              , @Field("user_id") user_id: String
                              , @Field("product_id") product_id: String
                              , @Field("device_id") device_id: String
                              , @Field("device_type") device_type: String): Call<AddToCartPojo>


    @FormUrlEncoded
    @POST("customerSupport")
    fun contactSupport(@Header("auth_key") auth_key: String
               , @Field("name") name: String
               , @Field("email") email: String
               , @Field("phone") phone: String
               , @Field("subject") subject: String
               , @Field("message") message: String): Call<SignUpPojo>


    @FormUrlEncoded
    @POST("getLoyaltyPoints")
    fun getLoyaltyPoints(@Header("auth_key") auth_key: String
                    , @Field("user_id") user_id: String
                    , @Field("device_id") device_id: String
                    , @Field("device_type") device_type: String): Call<LoyaltyPointsPojo>

    @FormUrlEncoded
    @POST("getMembership")
    fun getMembership(@Header("auth_key") auth_key: String
                    , @Field("user_id") user_id: String
                    , @Field("device_id") device_id: String
                    , @Field("device_type") device_type: String): Call<MembershipPojo>

    @FormUrlEncoded
    @POST("upgradeMembership")
    fun upgradeMembership(@Header("auth_key") auth_key: String
                    , @Field("user_id") user_id: String
                    , @Field("membership_id") membership_id: String
                    , @Field("device_id") device_id: String
                    , @Field("device_type") device_type: String): Call<MembershipPojo>


    @FormUrlEncoded
    @POST("accountAction")
    fun accountAction(@Header("auth_key") auth_key: String
                       , @Field("user_id") user_id: String
                       , @Field("type") type: String
                       , @Field("leaving_reason") leaving_reason: String
                       , @Field("password") password: String
                       , @Field("device_id") device_id: String
                       , @Field("device_type") device_type: String): Call<ChangePasswordPojo>


    @FormUrlEncoded
    @POST("trackOrder")
    fun trackOrder(@Header("auth_key") auth_key: String
                    , @Field("order_id") order_id: String
                    , @Field("device_id") device_id: String
                    , @Field("device_type") device_type: String): Call<TrackOrderPojo>


}
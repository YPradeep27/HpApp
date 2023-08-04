package com.hellopharmacy.views.fragments.orderhistory

data class OrderHistoryPojo(
    var message: String? = null,
    var orderArray: MutableList<OrderArray>? = null,
    var status: Boolean = false
) {
    data class OrderArray(
        var addressArr: AddressArr? = null,
        var address_id: Int? = null,
        var amount: Int? = null,
        var carts: MutableList<Cart>? = null,
        var carts_product_ids: String? = null,
        var created_at: String? = null,
        var id: String? = null,
        var invoice_link: String? = null,
        var order_id: String? = null,
        var payment: String? = null,
        var track: String? = null,
        var user_id: String? = null
    ) {
        data class AddressArr(
            var address_type: String? = null,
            var area: String? = null,
            var city: String? = null,
            var country: String? = null,
            var created_at: String? = null,
            var delete_status: Int? = null,
            var deleted_at: Any? = null,
            var flat_no: String? = null,
            var id: Int? = null,
            var landmark: String? = null,
            var phone_no: String? = null,
            var state: String? = null,
            var updated_at: String? = null,
            var user_id: Int? = null,
            var user_name: String? = null,
            var zipcode: String? = null
        )

        data class Cart(
            var id: String? = null,
            var image: String? = null,
            var name: String? = null,
            var description: String? = null,
            var price: Int? = null,
            var product_id: String? = null,
            var quantity: String? = null,
            var rating: String? = null,
            var size: String? = null,
            var total: String? = null,
            var total_ratings: Int? = null
        )
    }
}
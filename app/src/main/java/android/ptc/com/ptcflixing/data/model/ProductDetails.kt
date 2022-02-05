package android.ptc.com.ptcflixing.data.model


import com.google.gson.annotations.SerializedName

data class ProductDetails(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("success")
    val success: Boolean
) {
    data class Metadata(
        @SerializedName("brand")
        val brand: String,
        @SerializedName("image_list")
        val imageList: List<String>,
        @SerializedName("max_saving_percentage")
        val maxSavingPercentage: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("rating")
        val rating: Rating,
        @SerializedName("seller_entity")
        val sellerEntity: SellerEntity,
        @SerializedName("sku")
        val sku: String,
        @SerializedName("special_price")
        val specialPrice: Int,
        @SerializedName("summary")
        val summary: Summary
    ) {
        data class Rating(
            @SerializedName("average")
            val average: Int,
            @SerializedName("ratings_total")
            val ratingsTotal: Int
        )

        data class SellerEntity(
            @SerializedName("delivery_time")
            val deliveryTime: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String
        )

        data class Summary(
            @SerializedName("description")
            val description: String,
            @SerializedName("short_description")
            val shortDescription: String
        )
    }
}
package android.ptc.com.ptcflixing.data.model


import com.google.gson.annotations.SerializedName

data class ProductSearch(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("success")
    val success: Boolean
) {
    data class Metadata(
        @SerializedName("results")
        val results: List<Result>,
        @SerializedName("sort")
        val sort: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("total_products")
        val totalProducts: Int
    ) {
        data class Result(
            @SerializedName("brand")
            val brand: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("max_saving_percentage")
            val maxSavingPercentage: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("price")
            val price: Int,
            @SerializedName("rating_average")
            val ratingAverage: Int,
            @SerializedName("sku")
            val sku: String,
            @SerializedName("special_price")
            val specialPrice: Int
        )
    }
}
package android.ptc.com.ptcflixing.data.model


import com.google.gson.annotations.SerializedName

data class Config(
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("session")
    val session: Session,
    @SerializedName("success")
    val success: Boolean
) {
    data class Metadata(
        @SerializedName("currency")
        val currency: Currency,
        @SerializedName("languages")
        val languages: List<Language>,
        @SerializedName("support")
        val support: Support
    ) {
        data class Currency(
            @SerializedName("currency_symbol")
            val currencySymbol: String,
            @SerializedName("decimals")
            val decimals: Int,
            @SerializedName("decimals_sep")
            val decimalsSep: String,
            @SerializedName("iso")
            val iso: String,
            @SerializedName("position")
            val position: Int,
            @SerializedName("thousands_sep")
            val thousandsSep: String
        )

        data class Language(
            @SerializedName("code")
            val code: String,
            @SerializedName("default")
            val default: Boolean,
            @SerializedName("name")
            val name: String
        )

        data class Support(
            @SerializedName("call_to_order_enabled")
            val callToOrderEnabled: Boolean,
            @SerializedName("cs_email")
            val csEmail: String,
            @SerializedName("phone_number")
            val phoneNumber: String
        )
    }

    data class Session(
        @SerializedName("expire")
        val expire: Any,
        @SerializedName("id")
        val id: String,
        @SerializedName("YII_CSRF_TOKEN")
        val yIICSRFTOKEN: String
    )
}
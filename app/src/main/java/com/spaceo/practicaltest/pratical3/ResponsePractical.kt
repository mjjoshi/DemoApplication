package com.spaceo.practicaltest.pratical3

data class ResponsePractical(
    val CartCount: Int,
    val ClosedAccount: Int,
    val Message: String,
    val PremiumMerchant: List<PremiumMerchant>,
    val RecordCount: String,
    val Result: List<Result>,
    val Status: Int,
    val SubCategoryList: List<Any>,
    val bannerImages: List<BannerImage>,
    val banner_enable: Int,
    val loginauth: Int
)
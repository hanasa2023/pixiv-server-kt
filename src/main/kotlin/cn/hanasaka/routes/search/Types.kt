package cn.hanasaka.routes.search

val orderType = mapOf(
	"date" to "date_d",
	"popular" to "popular_d",
)

val modeType = mapOf(
	"tag" to "s_tag",
	"full_tag" to "s_tag_full",
)

val restrictType = mapOf(
	"all" to "all",
	"r18" to "r18",
	"safe" to "safe",
)

val searchPath = mapOf(
	"illust" to "illustrations",
	"manga" to "manga",
	"artworks" to "artworks",
)

val searchType = mapOf(
	"illust" to "illust_and_ugoira",
	"manga" to "manga",
	"artworks" to "all",
)

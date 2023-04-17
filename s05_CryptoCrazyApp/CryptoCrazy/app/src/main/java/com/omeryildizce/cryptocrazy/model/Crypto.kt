
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
data class Crypto(
    @SerializedName("additional_notices")
    @Expose
    val additionalNotices: List<String>,
    @SerializedName("asset_platform_id")
    @Expose
    val assetPlatformId: String,
    @SerializedName("block_time_in_minutes")
    @Expose
    val blockTimeInMinutes: Int,
    @SerializedName("categories")
    @Expose
    val categories: List<String>,
    @SerializedName("coingecko_rank")
    @Expose
    val coingeckoRank: Int,
    @SerializedName("coingecko_score")
    @Expose
    val coingeckoScore: Double,
    @SerializedName("community_score")
    @Expose
    val communityScore: Double,
    @SerializedName("contract_address")
    @Expose
    val contractAddress: String,
    @SerializedName("country_origin")
    @Expose
    val countryOrigin: String,
    @SerializedName("description")
    @Expose
    val description: Description,
    @SerializedName("detail_platforms")
    @Expose
    val detailPlatforms: DetailPlatforms,
    @SerializedName("developer_score")
    @Expose
    val developerScore: Double,
    @SerializedName("genesis_date")
    @Expose
    val genesisDate: Any,
    @SerializedName("hashing_algorithm")
    @Expose
    val hashingAlgorithm: Any,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("image")
    @Expose
    val image: Image,
    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String,
    @SerializedName("links")
    @Expose
    val links: Links,
    @SerializedName("liquidity_score")
    @Expose
    val liquidityScore: Double,
    @SerializedName("market_cap_rank")
    @Expose
    val marketCapRank: Any,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("platforms")
    @Expose
    val platforms: Platforms,
    @SerializedName("public_notice")
    @Expose
    val publicNotice: Any,
    @SerializedName("public_interest_score")
    @Expose
    val publicInterestScore: Double,
    @SerializedName("public_interest_stats")
    @Expose
    val publicInterestStats: PublicInterestStats,
    @SerializedName("sentiment_votes_down_percentage")
    @Expose
    val sentimentVotesDownPercentage: Any,
    @SerializedName("sentiment_votes_up_percentage")
    @Expose
    val sentimentVotesUpPercentage: Any,
    @SerializedName("status_updates")
    @Expose
    val statusUpdates: List<Any>,
    @SerializedName("symbol")
    @Expose
    val symbol: String
) {
    data class Description(
        @SerializedName("en")
        @Expose
        val en: String
    )

    data class DetailPlatforms(
        @SerializedName("binance-smart-chain")
        @Expose
        val binanceSmartChain: BinanceSmartChain
    ) {
        data class BinanceSmartChain(
            @SerializedName("contract_address")
            @Expose
            val contractAddress: String,
            @SerializedName("decimal_place")
            @Expose
            val decimalPlace: Int
        )
    }

    data class Image(
        @SerializedName("large")
        @Expose
        val large: String,
        @SerializedName("small")
        @Expose
        val small: String,
        @SerializedName("thumb")
        @Expose
        val thumb: String
    )

    data class Links(
        @SerializedName("announcement_url")
        @Expose
        val announcementUrl: List<String>,
        @SerializedName("bitcointalk_thread_identifier")
        @Expose
        val bitcointalkThreadIdentifier: Any,
        @SerializedName("blockchain_site")
        @Expose
        val blockchainSite: List<String>,
        @SerializedName("chat_url")
        @Expose
        val chatUrl: List<String>,
        @SerializedName("facebook_username")
        @Expose
        val facebookUsername: String,
        @SerializedName("homepage")
        @Expose
        val homepage: List<String>,
        @SerializedName("official_forum_url")
        @Expose
        val officialForumUrl: List<String>,
        @SerializedName("repos_url")
        @Expose
        val reposUrl: ReposUrl,
        @SerializedName("subreddit_url")
        @Expose
        val subredditUrl: Any,
        @SerializedName("telegram_channel_identifier")
        @Expose
        val telegramChannelIdentifier: String,
        @SerializedName("twitter_screen_name")
        @Expose
        val twitterScreenName: String
    ) {
        data class ReposUrl(
            @SerializedName("bitbucket")
            @Expose
            val bitbucket: List<Any>,
            @SerializedName("github")
            @Expose
            val github: List<Any>
        )
    }

    data class Platforms(
        @SerializedName("binance-smart-chain")
        @Expose
        val binanceSmartChain: String
    )

    data class PublicInterestStats(
        @SerializedName("alexa_rank")
        @Expose
        val alexaRank: Any,
        @SerializedName("bing_matches")
        @Expose
        val bingMatches: Any
    )
}
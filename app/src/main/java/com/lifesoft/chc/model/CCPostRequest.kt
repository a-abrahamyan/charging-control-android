package com.lifesoft.chc.model


data class CCPostRequest(
        val transactions: List<Transaction>
) {

    data class Transaction(
            val type: String,
            val text: String,
            val date: String,
            val id: String
    )
}
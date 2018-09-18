package com.lifesoft.chc.model


data class CCTransactions(
        val count: String,
        val transactions: List<Transaction>
) {

    data class Transaction(
            val date: String,
            val type: String,
            val amount: String,
            val before: String,
            val after: String,
            val success: String,
            val id: String
    )
}
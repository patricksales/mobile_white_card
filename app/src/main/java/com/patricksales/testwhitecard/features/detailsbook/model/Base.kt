package com.patricksales.testwhitecard.features.detailsbook.model

data class Base(
    val label: String?,
    val ref: String?,
    val repo: Repo,
    val sha: String?,
    val user: User
)
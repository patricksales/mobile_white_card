package com.patricksales.testwhitecard.features.detailsbook.model

data class Links(
    val comments: Comments,
    val commits: Commits,
    val html: Html,
    val issue: Issue,
    val review_comment: ReviewComment,
    val review_comments: ReviewComment,
    val self: Self,
    val statuses: Statuses
)
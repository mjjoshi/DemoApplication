package com.spaceo.practicaltest.Practical1.Model

data class ResponseData(
    val Message: String,
    val RecordCount: String,
    val Result: List<Result>,
    val Status: Int
)
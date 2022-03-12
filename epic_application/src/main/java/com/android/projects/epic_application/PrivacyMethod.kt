package com.android.projects.epic_application

data class PrivacyMethod(
    val methods: List<Method>
)

data class Method(
    val message: String,
    val name_regex: String
)
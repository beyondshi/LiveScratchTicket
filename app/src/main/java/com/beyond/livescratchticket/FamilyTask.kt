package com.beyond.livescratchticket

import kotlinx.serialization.Serializable

@Serializable
data class FamilyTask(
    val name: String,
    val roleImage: RoleImage,
)

@Serializable
data class RoleImage(
    val manImg: Int,
    val womanImg: Int
)

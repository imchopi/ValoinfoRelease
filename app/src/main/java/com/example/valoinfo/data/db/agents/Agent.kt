package com.example.valoinfo.data.db.agents

data class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val isPlayableCharacter: Boolean,
    var isChecked: Boolean = false
)

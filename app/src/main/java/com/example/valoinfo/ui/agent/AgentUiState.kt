package com.example.valoinfo.ui.agent

import com.example.valoinfo.data.db.agents.Agent

data class AgentUiState (
    val agent:List<Agent>,
    val errorMessage:String?=null,
)
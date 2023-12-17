package com.example.valoinfo.ui.tierlist

import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.data.db.tierlist.Tierlist

data class TierlistUiState(
    val tierlist:List<Tierlist>,
    val errorMessage:String?=null,
)

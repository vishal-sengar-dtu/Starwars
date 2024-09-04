package com.example.starwars.viewmodel

import android.service.autofill.FieldClassification.Match
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.model.apimodel.MatchList
import com.example.starwars.model.apimodel.MatchListItem
import com.example.starwars.model.apimodel.Player
import com.example.starwars.model.apimodel.PlayerList
import com.example.starwars.model.apimodel.PlayerListItem
import com.example.starwars.model.uimodel.MatchResult
import com.example.starwars.model.uimodel.UIMatchList
import com.example.starwars.model.uimodel.UIMatchListItem
import com.example.starwars.model.uimodel.UIPlayer
import com.example.starwars.model.uimodel.UIPlayerList
import com.example.starwars.model.uimodel.UIPlayerListItem
import com.example.starwars.repository.StarwarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerViewModel(private val repository: StarwarsRepository) : ViewModel() {
    private lateinit var apiPlayerList : PlayerList
    private lateinit var apiMatchList : MatchList
    var player : UIPlayerListItem? = null

    private var _playerList = MutableLiveData<UIPlayerList>()
    val playerList : LiveData<UIPlayerList>
        get() = _playerList

    private var _matchList = MutableLiveData<UIMatchList>()
    val matchList : LiveData<UIMatchList>
        get() = _matchList

    init {
        viewModelScope.launch {
            apiPlayerList = withContext(Dispatchers.IO) {repository.getPlayerList()}
            apiMatchList = withContext(Dispatchers.IO) {repository.getMatchList()}

            _playerList.postValue(getPlayerListWithScore())
        }
    }

    public fun filterMatchList() {
        var playerMatchList = UIMatchList()

        apiMatchList.forEach {
            when(player?.id) {
                it.player1.id -> {
                    playerMatchList.add(UIMatchListItem(
                        player1 = UIPlayer(
                            id = it.player1.id,
                            name = getPlayerNameFromId(it.player1.id),
                            score = it.player1.score,
                            result = getMatchResult(it, it.player1)
                        ),
                        player2 = UIPlayer(
                            id = it.player2.id,
                            name = getPlayerNameFromId(it.player2.id),
                            score = it.player2.score,
                            result = getMatchResult(it, it.player2)
                        )
                    ))
                }
                it.player2.id -> {
                    playerMatchList.add(UIMatchListItem(
                        player1 = UIPlayer(
                            id = it.player1.id,
                            name = getPlayerNameFromId(it.player1.id),
                            score = it.player1.score,
                            result = getMatchResult(it, it.player1)
                        ),
                        player2 = UIPlayer(
                            id = it.player2.id,
                            name = getPlayerNameFromId(it.player2.id),
                            score = it.player2.score,
                            result = getMatchResult(it, it.player2)
                        )
                    ))
                }
            }
        }

        _matchList.value = playerMatchList
    }

    private fun getMatchResult(match: MatchListItem, player: Player): MatchResult {
        when(player) {
            match.player1 -> {
                return if(match.player1.score > match.player2.score) MatchResult.Win
                else if(match.player1.score == match.player2.score) MatchResult.Draw
                else MatchResult.Loss
            }
            match.player2 -> {
                return if(match.player2.score > match.player1.score) MatchResult.Win
                else if(match.player1.score == match.player2.score) MatchResult.Draw
                else MatchResult.Loss
            }
        }
        return MatchResult.Draw
    }

    private fun getPlayerNameFromId(id: Int): String {
        var name = String()

        apiPlayerList.forEach {
            if(it.id == id) {
                name = it.name
            }
        }

        return name
    }

    private fun getPlayerListWithScore() : UIPlayerList {
        val uiPlayerList = UIPlayerList()

        apiPlayerList.forEach {
            uiPlayerList.add(
                UIPlayerListItem(
                    id = it.id,
                    icon = it.icon,
                    name = it.name,
                    score = calculateScore(it)
                )
            )
        }

        return  uiPlayerList
    }

    private fun calculateScore(player : PlayerListItem) : Int {
        var score = 0

        apiMatchList.forEach { match ->
            when (player.id) {
                match.player1.id -> {
                    when {
                        match.player1.score > match.player2.score -> score += 3
                        match.player1.score == match.player2.score -> score += 1
                    }
                }
                match.player2.id -> {
                    when {
                        match.player2.score > match.player1.score -> score += 3
                        match.player1.score == match.player2.score -> score += 1
                    }
                }
            }
        }
        return score
    }

}

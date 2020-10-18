package com.example.madlevel4task2

import android.content.Context

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getGames(): List<Game> = gameDao.getGames()

    suspend fun getWinCount(): Int = gameDao.getWinCount()
    suspend fun getLoseCount(): Int = gameDao.getLoseCount()
    suspend fun getDrawCount(): Int = gameDao.getDrawCount()

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

    suspend fun deleteGame(game: Game) = gameDao.deleteGame(game)

    suspend fun deleteAllGames() = gameDao.deleteAllGames()

}
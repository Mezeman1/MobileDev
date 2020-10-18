package com.example.madlevel4task2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    suspend fun getGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("SELECT COUNT(*) fROM games WHERE result = 0")
    suspend fun getWinCount() : Int

    @Query("SELECT COUNT(*) fROM games WHERE result = 1")
    suspend fun getLoseCount(): Int

    @Query("SELECT COUNT(*) fROM games WHERE result = 2")
    suspend fun getDrawCount(): Int
}
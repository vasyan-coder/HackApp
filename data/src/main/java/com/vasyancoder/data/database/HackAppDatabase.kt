package com.vasyancoder.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vasyancoder.data.database.dao.UserDao
import com.vasyancoder.data.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
abstract class HackAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: HackAppDatabase? = null

        fun getDatabase(context: Context): HackAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HackAppDatabase::class.java,
                    "hack_app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.vasyancoder.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vasyancoder.data.database.dao.CalendarDao
import com.vasyancoder.data.database.dao.HackathonDao
import com.vasyancoder.data.database.dao.UserDao
import com.vasyancoder.data.database.model.CalendarItemModel
import com.vasyancoder.data.database.model.HackathonModel
import com.vasyancoder.data.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, HackathonModel::class, CalendarItemModel::class],
    version = 1,
    exportSchema = true
)
abstract class HackAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun hackathonDao(): HackathonDao
    abstract fun calendarDao(): CalendarDao

    companion object {

        @Volatile
        private var INSTANCE: HackAppDatabase? = null

        fun getDatabase(context: Context): HackAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HackAppDatabase::class.java,
                    "hack_app_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
//                            INSTANCE?.userDao()?.insertUser(
//                                User(
//                                    uid = 0,
//                                    login = "test",
//                                    password = "123",
//                                    email = "test@mail.com"
//                                )
//                            )

                            INSTANCE?.userDao()?.insertUser(
                                User(
                                    uid = 0,
                                    login = "admin",
                                    password = "12345",
                                    email = "admin@mail.com",
                                    role = "admin"
                                )
                            )

//                            INSTANCE?.hackathonDao()?.addHackathon(
//                                HackathonModel(
//                                    id = 0,
//                                    name = "TEST",
//                                    organization = "MTC",
//                                    dates = "Регистрация до: 22 марта\nПроходит: с 24 до 30 марта",
//                                    status = "Online"
//                                )
//                            )
//                            INSTANCE?.calendarDao()?.addCalendarItem(
//                                CalendarItemModel(
//                                    id = 0,
//                                    name = "Test",
//                                    organization = "MTC",
//                                    dates = "Dates",
//                                    status = "Online"
//                                )
//                            )
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.example.contacts_roomdb



import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun roomDao(): ContactsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "contactsDB"
            ).build()

                INSTANCE = instance
                instance
            }
            Log.d("abcd", "DataBase Created")
        }
    }
}

// Repository
// viewModel
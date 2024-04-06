package com.example.contacts_roomdb



import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2)
abstract class AppDatabase : RoomDatabase() {



    abstract fun roomDao(): ContactsDao

    companion object {

        val migration_1_2 = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE Contact RENAME COLUMN contactName to cName;")
//                    db.execSQL("ALTER TABLE Contacts ADD COLUMN contactEmail VARCHAR(55) DEFAULT 'noMail' ")
            }

        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "contactsDB"
            )
                    .addMigrations(migration_1_2)
                    .build()

                INSTANCE = instance
                instance
            }
            Log.d("abcd", "DataBase Created")
        }
    }
}

// Repository
// viewModel
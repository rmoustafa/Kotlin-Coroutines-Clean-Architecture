package training.ram.kotlinCleanArchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import training.ram.kotlinCleanArchitecture.data.entities.Customer

/**
 * The Room database that contains the customer table
 */
@Database(entities = arrayOf(Customer::class), version = 1)
abstract class CustomersDatabase : RoomDatabase(){
    abstract fun customerDao(): CustomerDAO
}
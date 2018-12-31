package training.ram.kotlinCleanArchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import timber.log.Timber
import training.ram.kotlinCleanArchitecture.data.entities.Customer

/**
 * Data Access Object for the customers table.
 */
@Dao
interface CustomerDAO {

    /**
     * Get customer list from the table,
     * this query gets all customers from the table
     *
     * @return
     */
    @Query("SELECT * FROM customers")
    fun getCustomers(): List<Customer>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCustomers(vararg customers: Customer)

    /**
     * Delete all customers.
     */
    @Query("DELETE FROM customers")
    fun deleteAllCustomers()

}
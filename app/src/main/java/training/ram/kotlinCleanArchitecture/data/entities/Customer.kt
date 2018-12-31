package training.ram.kotlinCleanArchitecture.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Customers")
data class Customer(
    @PrimaryKey @ColumnInfo(name = "customerid")
    @SerializedName("id")
    val id:Int,
    @ColumnInfo(name = "fname")
    @SerializedName("customerFirstName")
    val customerFirstName: String,
    @ColumnInfo(name = "lname")
    @SerializedName("customerLastName")
    val customerLastName: String
)
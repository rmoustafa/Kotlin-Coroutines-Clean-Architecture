package training.ram.kotlinCleanArchitecture.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import training.ram.kotlinCleanArchitecture.data.local.CustomerDAO
import training.ram.kotlinCleanArchitecture.data.local.CustomersDatabase
import javax.inject.Singleton

@Module
object RoomDBModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideRoomDB(context: Context): CustomersDatabase {
        return Room.databaseBuilder(
            context,
            CustomersDatabase::class.java, "customerDb.db"
        )
        .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideCustomerDao(db: CustomersDatabase): CustomerDAO {
        return db.customerDao()
    }

}
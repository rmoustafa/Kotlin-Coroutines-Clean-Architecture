package training.ram.kotlinCleanArchitecture.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import training.ram.kotlinCleanArchitecture.base.SampleApplication
import training.ram.kotlinCleanArchitecture.data.local.CustomerDAO
import training.ram.kotlinCleanArchitecture.data.local.CustomersDatabase
import training.ram.kotlinCleanArchitecture.di.scopes.ApplicationScope

@Module
class RoomDBModule {
    @ApplicationScope
    @Provides
    fun provideRoomDB(context: SampleApplication): CustomersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CustomersDatabase::class.java, "customerDb.db"
        )
        .build()
    }

    @ApplicationScope
    @Provides
    fun provideCustomerDao(db: CustomersDatabase): CustomerDAO {
        return db.customerDao()
    }

}
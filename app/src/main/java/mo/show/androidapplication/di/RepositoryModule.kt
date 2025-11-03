package mo.show.androidapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mo.show.androidapplication.store.data.repository.ProductsRepositoryImpl
import mo.show.androidapplication.store.domain.repository.ProductsRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
 abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository
}




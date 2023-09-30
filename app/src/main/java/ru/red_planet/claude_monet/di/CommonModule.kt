package ru.red_planet.claude_monet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.red_planet.claude_monet.data.local.cart.CartStoreImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideCartStore(): CartStoreImp = CartStoreImp()
}

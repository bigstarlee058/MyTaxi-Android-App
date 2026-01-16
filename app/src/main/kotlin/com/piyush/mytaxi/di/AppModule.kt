package com.piyush.mytaxi.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.piyush.mytaxi.data.repository.AuthRepositoryImpl
import com.piyush.mytaxi.data.repository.DriverRepositoryImpl
import com.piyush.mytaxi.data.repository.LocationRepositoryImpl
import com.piyush.mytaxi.domain.repository.AuthRepository
import com.piyush.mytaxi.domain.repository.DriverRepository
import com.piyush.mytaxi.domain.repository.LocationRepository
import com.piyush.mytaxi.domain.use_case.GetCurrentLocationUseCase
import com.piyush.mytaxi.domain.use_case.GetNearbyDriversUseCase
import com.piyush.mytaxi.domain.use_case.SendOtpUseCase
import com.piyush.mytaxi.domain.use_case.VerifyOtpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideSendOtpUseCase(authRepository: AuthRepository): SendOtpUseCase = SendOtpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideVerifyOtpUseCase(authRepository: AuthRepository): VerifyOtpUseCase = VerifyOtpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideLocationRepository(@ApplicationContext context: Context): LocationRepository = LocationRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideGetCurrentLocationUseCase(locationRepository: LocationRepository): GetCurrentLocationUseCase = GetCurrentLocationUseCase(locationRepository)

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideDriverRepository(firestore: FirebaseFirestore): DriverRepository = DriverRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideGetNearbyDriversUseCase(driverRepository: DriverRepository): GetNearbyDriversUseCase = GetNearbyDriversUseCase(driverRepository)
}
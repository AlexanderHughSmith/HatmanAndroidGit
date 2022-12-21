package com.zanhsmitty.hatman.ui

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.zanhsmitty.hatman.data.*
import com.zanhsmitty.hatman.di.DatabaseModuleTest
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mockk.mockk

class SharedViewModelTest(context: Context) {

    //private val database = DatabaseModuleTest.provideDatabase()
    //private val dao = DatabaseModuleTest.provideDao(database)
    private val database = DatabaseModuleTest.provideDatabase(context)
    private val dao = DatabaseModuleTest.provideDao(database)
    private val repository = DatabaseModuleTest.provideHatmanRepository(dao)
    //private val repository: HatmanRepository= mockk(relaxed = true)
    private val dataStore: HatmanDataStore = mockk(relaxed = true)

    val viewModel = SharedViewModel(repository, dataStore)
}
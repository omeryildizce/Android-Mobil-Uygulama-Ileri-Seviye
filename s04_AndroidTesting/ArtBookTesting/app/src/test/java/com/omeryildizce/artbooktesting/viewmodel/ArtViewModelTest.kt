package com.omeryildizce.artbooktesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.omeryildizce.artbooktesting.MainCoroutineRule
import com.omeryildizce.artbooktesting.getOrAwaitValueTest
import com.omeryildizce.artbooktesting.repo.ArtRepository
import com.omeryildizce.artbooktesting.repo.FakeArtRepository
import com.omeryildizce.artbooktesting.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        // Test Doubles

        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`(){
            viewModel.makeArt("Mona Lisa","Da vinci", "")
            val value = viewModel.insertArtMessage.getOrAwaitValueTest()
            assertThat(value.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert art without name returns error`(){
        viewModel.makeArt("","Da vinci", "2020")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert art without artistName returns error`(){
        viewModel.makeArt("Mona Lisa","", "2020")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

}
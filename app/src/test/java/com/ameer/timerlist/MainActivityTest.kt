package com.ameer.timerlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.Assert.assertEquals

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testStartTimer() = runTest {
        viewModel.startTimer(0)
        advanceTimeBy(1100) // Fast-forward time
        assertEquals(1, viewModel.timers.value[0])
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher after tests
    }
}

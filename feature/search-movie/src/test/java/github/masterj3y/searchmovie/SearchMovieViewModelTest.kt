package github.masterj3y.searchmovie

import github.masterj3y.searchmovie.mockdata.MockData
import github.masterj3y.subscene.data.SubtitleRepository
import github.masterj3y.testutils.coroutine.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SearchMovieViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val repository: SubtitleRepository = mock()
    private val viewModel = SearchMovieViewModel(repository)

    @Test
    fun `test search movie event`() = runTest {

        val mockData = MockData.mockSearchMovieResult

        whenever(repository.searchMovieByTitle("hello")).thenReturn(flowOf(mockData))

        viewModel.onEvent(SearchMovieEvent.Search("hello"))

        val result = viewModel.state
            .filter {
                it is SearchMovieState.Result
            }
            .map {
                it as SearchMovieState.Result
            }.firstOrNull()

        result shouldNotBe null
        result?.movies?.size shouldBe 2
    }
}
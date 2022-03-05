package github.masterj3y.subscene.extractor.movie

import github.masterj3y.subscene.extractor.Extractor
import github.masterj3y.testutils.base.ResourceReader
import io.kotest.matchers.shouldBe
import org.junit.Test

class SearchMovieResultExtractorTest : ResourceReader() {

    @Test
    fun testExtract() {
        val response = readApiResponse("search-movie-response.html")
        val extractor: Extractor<List<String>?> = SearchMovieResultExtractor()
        val list = extractor.extract(response)
        list?.size shouldBe 23
    }
}
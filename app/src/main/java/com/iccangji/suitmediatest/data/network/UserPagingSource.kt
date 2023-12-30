import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iccangji.suitmediatest.data.network.retrofit.ApiService
import com.iccangji.suitmediatest.data.network.model.DataItem

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val data = apiService.getUsersData(nextPageNumber)
            LoadResult.Page(
                data = data.data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (data.data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

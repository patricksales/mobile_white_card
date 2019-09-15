package com.patricksales.testwhitecard.features.detailsbook.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.patricksales.testwhitecard.core.data.RemoteDataSource
import com.patricksales.testwhitecard.core.data.Repository
import com.patricksales.testwhitecard.core.data.api.ResponseApi
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class DetailBookViewModelTest {

    lateinit var viewModel: DetailBookViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val repository = Repository(RemoteDataSource)
        viewModel = Mockito.spy(DetailBookViewModel(repository))
    }

    @Test
    fun `verifica se é setado corretamente a erro no liveData em caso de falha`() {
        val list = listOf<Any>()
        val messageError = "NOK"
        val responseApi = ResponseApi(
            status = ResponseApi.StatusResponse.ERROR,
            message = messageError,
            data = list
        )

        viewModel.validateReturn(responseApi)

        Assert.assertEquals(viewModel.showError.value, messageError)
    }

    @Test
    fun `verifica se é setado corretamente a lista no liveData em caso de sucesso`() {
        val list: List<PullRepository> = listOf(
            PullRepository(
                url = "https://api.github.com/repos/CyC2018/CS-Notes/pulls/785",
                id = 315354332,
                node_id = "MDExOlB1bGxSZXF1ZXN0MzE1MzU0MzMy",
                html_url = "https://github.com/CyC2018/CS-Notes/pull/785",
                diff_url = "https://github.com/CyC2018/CS-Notes/pull/785.diff",
                patch_url = "https://github.com/CyC2018/CS-Notes/pull/785.patch",
                issue_url = "https://api.github.com/repos/CyC2018/CS-Notes/issues/785",
                number = 785,
                state = "open",
                locked = false,
                title = "间隙锁前开后闭",
                body = "Next-Key Locks都是前开后闭区间，实现上，InnoDB给每个索引加了一个不存在的最大值supremum， 这样才符合“都是前开后闭区间”的定义。",
                created_at = "2019-09-09T04:41:12Z",
                updated_at = "2019-09-09T04:41:12Z",
                closed_at = null,
                merged_at = null,
                merge_commit_sha = "7b454c83c55c2c8b62d85f3cc72c4753ff23db43",
                assignee = null,
                assignees = listOf(),
                requested_reviewers = listOf(),
                requested_teams = listOf(),
                labels = listOf(),
                milestone = null,
                commits_url = "https://api.github.com/repos/CyC2018/CS-Notes/pulls/785/commits",
                review_comments_url = "https://api.github.com/repos/CyC2018/CS-Notes/pulls/785/comments",
                review_comment_url = "https://api.github.com/repos/CyC2018/CS-Notes/pulls/comments{/number}",
                comments_url = "https://api.github.com/repos/CyC2018/CS-Notes/issues/785/comments",
                statuses_url = "https://api.github.com/repos/CyC2018/CS-Notes/statuses/ad5caff2288069375e88082bbb36ceca412145ed",
                head = null,
                author_association = "NONE",
                base = null,
                _links = null,
                user = null
            )
        )
        val responseApi = ResponseApi(
            status = ResponseApi.StatusResponse.SUCCESS,
            message = "OK",
            data = list
        )

        viewModel.validateReturn(responseApi)

        Assert.assertEquals(viewModel.repositoryLiveData.value, list)
    }

    @Test
    fun `verifica se é setado o liveData em caso de sucesso porém com a lista vazia`() {
        val list = listOf<Any>()
        val responseApi = ResponseApi(
            status = ResponseApi.StatusResponse.SUCCESS,
            message = "OK",
            data = list
        )

        viewModel.validateReturn(responseApi)

        Assert.assertEquals(viewModel.repositoryLiveData.value, null)
        Assert.assertNotEquals(viewModel.showError.value, "")
    }
}
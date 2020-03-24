package br.com.mobiplus.gitclient.presentation.ui.activity

import android.content.Intent
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import br.com.mobiplus.gitclient.R
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestAPIDataSource
import br.com.mobiplus.gitclient.data.pullRequest.PullRequestRepositoryImpl
import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.data.pullRequest.model.UserResponseModel
import br.com.mobiplus.gitclient.domain.FakeResultWrapper
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository
import br.com.mobiplus.gitclient.presentation.ui.pullRequest.details.PullRequestActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.util.*

@RunWith(AndroidJUnit4ClassRunner::class)
class PullRequestActivityTest {

    @get:Rule
    val rule = ActivityTestRule(PullRequestActivity::class.java, false, false)

    @MockK
    private lateinit var pullRequestAPIDataSource: PullRequestAPIDataSource

    @Before
    fun before() {

        MockKAnnotations.init(this)

        loadKoinModules(module {
            factory<PullRequestRepository>(override = true) {
                PullRequestRepositoryImpl(
                        pullRequestAPIDataSource
                )
            }
        })
    }

    @Test
    fun openActivity() {
        coEvery {
            pullRequestAPIDataSource.getPullRequest(any(), any(), any())
        } returns FakeResultWrapper.mockFullSuccess(
            success = PullRequestResponseModel(
                    number = 1,
                    htmlUrl = "",
                    title = "#FAKE_TITLE",
                    userModel = UserResponseModel(
                            login = "luis.fernandez",
                            avatarUrl = "http://avatar.url"
                    ),
                    body = "",
                    createdAt = Date(),
                    comments = 8,
                    commits = 4,
                    additions = 56,
                    deletions = 45,
                    changedFiles = 23
            )
        )

        val intent = Intent()
        intent.putExtra("owner", "TEST_OWNER")
        intent.putExtra("gitRepoName", "TEST_REPO")
        intent.putExtra("pullRequestNumber", 1000L)

        rule.launchActivity(intent)

        assertDisplayed(R.id.textAuthorName, "luis.fernandez")
        assertDisplayed(R.id.textTitle, "#FAKE_TITLE")
        assertDisplayed(R.id.textComments, "Comments: 8")
        assertDisplayed(R.id.textCommits, "Commits: 4")
        assertDisplayed(R.id.textAdditions, "Additions: 56")
        assertDisplayed(R.id.textDeletions, "Deletions: 45")
        assertDisplayed(R.id.textChangedFiles, "Changed Files: 23")
    }
}
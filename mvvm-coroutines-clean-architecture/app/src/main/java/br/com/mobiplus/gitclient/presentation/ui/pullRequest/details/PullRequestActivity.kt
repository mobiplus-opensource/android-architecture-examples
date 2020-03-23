package br.com.mobiplus.gitclient.presentation.ui.pullRequest.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.R
import br.com.mobiplus.gitclient.domain.extensions.toCacheFormat
import com.bumptech.glide.Glide
import br.com.mobiplus.gitclient.presentation.extensions.setGone
import br.com.mobiplus.gitclient.presentation.extensions.setVisible
import br.com.mobiplus.gitclient.presentation.ui.base.Navigator
import br.com.mobiplus.gitclient.presentation.ui.base.ViewState
import kotlinx.android.synthetic.main.activity_pull_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestActivity : AppCompatActivity() {
    companion object {
        fun open(from: Context, owner: String, gitRepoName: String, pullRequestNumber: Long) {
            val bundle = Bundle()

            bundle.putString("ownerModel", owner)
            bundle.putString("gitRepoName", gitRepoName)
            bundle.putLong("pullRequestNumber", pullRequestNumber)

            Navigator.goToActivity(from, PullRequestActivity::class.java, bundle)
        }
    }

    private val viewModel by viewModel<PullRequestViewModel>()

    private lateinit var owner: String
    private lateinit var gitRepoName: String
    private var pullRequestNumber: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        owner = intent.extras?.getString("ownerModel") ?: ""
        gitRepoName = intent.extras?.getString("gitRepoName") ?: ""
        pullRequestNumber = intent.extras?.getLong("pullRequestNumber") ?: -1

        this.initToolbar()
        this.initObservers()

        viewModel.getPullRequest(owner, gitRepoName, pullRequestNumber)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Pull Request Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initObservers() {
        viewModel.pullRequestState.observe(this, Observer {
            when (it) {
                is ViewState.Loading -> {
                    showLoadingView()
                }

                is ViewState.Success -> {
                    showContent(it.success)
                }

                is ViewState.Error -> {
                    showErrorView(it.error.toString())
                }
            }
        })
    }

    //region ViewStates
    private fun showLoadingView() {
        hideErrorView()
        hideContent()

        layoutLoading.setVisible()
    }

    private fun hideLoadingView() {
        layoutLoading.setGone()
    }

    private fun showErrorView(message: String) {
        hideLoadingView()
        hideContent()

        textPullRequestError.text = message
        layoutError.setVisible()
    }

    private fun hideErrorView() {
        layoutError.setGone()
        textPullRequestError.text = ""
    }

    @SuppressLint("SetTextI18n")
    private fun showContent(pullRequest: PullRequestModel) {
        hideLoadingView()
        hideErrorView()

        textAuthorName.text = pullRequest.user.login
        textTitle.text = pullRequest.title
        textDescription.text = pullRequest.body
        textComments.text = "Comments: ${pullRequest.comments}"
        textCommits.text = "Commits: ${pullRequest.commits}"
        textAdditions.text = "Additions: ${pullRequest.additions}"
        textDeletions.text = "Deletions: ${pullRequest.deletions}"
        textChangedFiles.text = "Changed Files: ${pullRequest.changedFiles}"

        textDate.text = pullRequest.createdAt.toCacheFormat()

        Glide.with(this).load(pullRequest.user.avatarUrl)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .into(imagePullRequestAuthor)

        layoutContent.setVisible()
    }

    private fun hideContent() {
        layoutContent.setGone()
    }
    //endregion
}

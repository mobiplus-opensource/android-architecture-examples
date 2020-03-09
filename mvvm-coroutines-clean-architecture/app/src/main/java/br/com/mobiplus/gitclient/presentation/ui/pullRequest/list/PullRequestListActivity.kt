package br.com.mobiplus.gitclient.presentation.ui.pullRequest.list

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import com.example.basearch.R
import br.com.mobiplus.gitclient.presentation.extensions.setGone
import br.com.mobiplus.gitclient.presentation.extensions.setVisible
import br.com.mobiplus.gitclient.presentation.extensions.showToast
import br.com.mobiplus.gitclient.presentation.ui.base.Navigator
import br.com.mobiplus.gitclient.presentation.ui.base.ViewState
import br.com.mobiplus.gitclient.presentation.ui.pullRequest.details.PullRequestActivity
import br.com.mobiplus.gitclient.presentation.ui.pullRequest.list.adapter.PullRequestListAdapter
import br.com.mobiplus.gitclient.presentation.ui.pullRequest.list.adapter.PullRequestListAdapterListener
import kotlinx.android.synthetic.main.activity_pull_request_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestListActivity : AppCompatActivity() {
    companion object {
        fun open(from: Context, owner: String, gitRepoName: String) {
            val bundle = Bundle()

            bundle.putString("ownerModel", owner)
            bundle.putString("gitRepoName", gitRepoName)

            Navigator.goToActivity(from, PullRequestListActivity::class.java, bundle)
        }
    }

    private val viewModel by viewModel<PullRequestListViewModel>()
    private lateinit var adapter: PullRequestListAdapter

    private lateinit var owner: String
    private lateinit var gitRepoName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)

        owner = intent.extras?.getString("ownerModel") ?: ""
        gitRepoName = intent.extras?.getString("gitRepoName") ?: ""

        this.initToolbar()
        this.initRecyclerView()
        this.initObservers()
        this.initListeners()

        viewModel.loadPullRequestList(
            owner = owner,
            gitRepoName = gitRepoName
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "$gitRepoName Pull Requests"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initObservers() {
        viewModel.pullRequestListState.observe(this, Observer {
            when (it) {
                is ViewState.Loading -> {
                    showLoadingView()
                }

                is ViewState.Success -> {
                    showContent(it.data)
                }

                is ViewState.Empty -> {
                    val message = getString(R.string.empty_list)

                    when {
                        adapter.isEmpty() -> showErrorView(message)
                        else -> showToast(message)
                    }
                }

                is ViewState.Error -> {
                    showErrorView(it.error.toString())
                }
            }
        })
    }

    private fun initListeners() {
        buttonPullRequestsTryAgain.setOnClickListener {
            viewModel.loadPullRequestList(
                owner = "",
                gitRepoName = ""
            )
        }
    }

    private fun initRecyclerView() {
        adapter = PullRequestListAdapter(
            this,
            mutableListOf(),
            object : PullRequestListAdapterListener {
                override fun onItemClick(pullRequestNumber: Long) {
                    PullRequestActivity.open(
                        this@PullRequestListActivity,
                        owner,
                        gitRepoName,
                        pullRequestNumber
                    )
                }
            }
        )

        recyclerViewPullRequests.layoutManager = LinearLayoutManager(this)
        recyclerViewPullRequests.adapter = adapter
    }

    //region ViewStates
    private fun showLoadingView() {
        hideErrorView()
        hideContent()

        layoutPullRequestsLoading.setVisible()
    }

    private fun hideLoadingView() {
        layoutPullRequestsLoading.setGone()
    }

    private fun showErrorView(message: String) {
        hideLoadingView()
        hideContent()

        textPullRequestsError.text = message
        layoutPullRequestsError.setVisible()
    }

    private fun hideErrorView() {
        layoutPullRequestsError.setGone()
        textPullRequestsError.text = ""
    }

    private fun showContent(pullRequestList: List<PullRequestModel>) {
        hideLoadingView()
        hideErrorView()

        adapter.addItems(pullRequestList)

        recyclerViewPullRequests.setVisible()
    }

    private fun hideContent() {
        recyclerViewPullRequests.setGone()
    }
    //endregion
}

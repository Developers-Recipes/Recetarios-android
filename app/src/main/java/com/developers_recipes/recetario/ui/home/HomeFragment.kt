package com.developers_recipes.recetario.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.developers_recipes.recetario.R
import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.presenters.ApiPresenter

class HomeFragment : Fragment(), ApiContract.View {

    val presenter: ApiContract.Presenter = ApiPresenter(this)

    private lateinit var appVersion: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        appVersion = root.findViewById(R.id.appVersion)
        presenter.fetchApiVersion()
        return root
    }

    override fun showApiVersion(version: String) {
        appVersion.text = version
    }
}
package com.developers_recipes.recetario.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.developers_recipes.recetario.services.api.ApiClient
import com.developers_recipes.recetario.R
import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.models.ApiVersion
import com.developers_recipes.recetario.models.ApiResponse
import com.developers_recipes.recetario.presenters.ApiPresenter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ApiContract.View{
    val presenter: ApiContract.Presenter = ApiPresenter(this)
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //getApiVersion()


    }

    private fun getApiVersion(){
        val service = ApiClient.getInstance()
        service?.getVersionName()?.enqueue(object : Callback<ApiResponse<ApiVersion>> {
            override fun onFailure(call: Call<ApiResponse<ApiVersion>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<ApiVersion>>,
                response: Response<ApiResponse<ApiVersion>>
            ){
                val body = response.body()
                body?.result?.getName()?.let { presenter.setApiVersion(it) }
            }

        })
    }

    override fun showApiVersion(version: String) {
//        appVersion.text = version
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}

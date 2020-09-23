package com.example.appiness.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appiness.R
import com.example.appiness.adapter.MainRecyclerAdapter
import com.example.appiness.model.ContentType
import com.example.appiness.model.DataModelEncapsulator
import com.example.appiness.viewmodel.MainActivityViewModel
import com.example.appiness.viewmodel.MainViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = ViewModelProviders.of(this, MainViewModelFactory(application))
            .get(MainActivityViewModel::class.java)
        initialise()

    }
    private fun initialise() {
        setContentView(R.layout.activity_main)
        main_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        showMainActivityList()
        setSupportActionBar(my_toolbar)

        ok.setOnClickListener {
            mainActivityViewModel.getList(editText.text.toString().trim(), false)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.getContentType() == ContentType.DATAMODEL) {
                        if ((it as DataModelEncapsulator).data!!.isNotEmpty())
                            main_recycler_view.adapter = MainRecyclerAdapter(
                                it.data!!, LayoutInflater.from(MainActivity@ this)
                            )
                        else {

                            Toast.makeText(applicationContext, "No Data found in DB search in webserver", Toast.LENGTH_LONG)
                                .show()
                            mainActivityViewModel.getList(editText.text.toString().trim(), true)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    if (it.getContentType() == ContentType.DATAMODEL) {
                                        main_recycler_view.adapter = MainRecyclerAdapter(
                                            (it as DataModelEncapsulator).data!!,
                                            LayoutInflater.from(MainActivity@ this)
                                        )

                                    }

                                }
                        }
                    }
                    search_edit_text.visibility = View.GONE
                }


        }
    }

    private fun showMainActivityList() {

        mainActivityViewModel.getList("abcd", true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                if (it.getContentType() == ContentType.DATAMODEL) {
                    if ((it as DataModelEncapsulator).data!!.isNotEmpty())
                        main_recycler_view.adapter = MainRecyclerAdapter(
                            it.data!!, LayoutInflater.from(MainActivity@ this)
                        )
                    else {
                        mainActivityViewModel.getList(editText.text.toString().trim(), false)
                        Toast.makeText(applicationContext, "No Data found in server", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            search_edit_text.visibility = View.VISIBLE
            return true
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}






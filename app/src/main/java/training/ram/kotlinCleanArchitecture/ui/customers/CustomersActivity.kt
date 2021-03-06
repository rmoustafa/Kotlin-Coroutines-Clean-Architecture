package training.ram.kotlinCleanArchitecture.ui.customers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_customers.*
import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlinCleanArchitecture.di.Injector
import training.ram.kotlin_cleanarchitecture.R.layout.activity_customers


class CustomersActivity : AppCompatActivity() {

    private lateinit var customerAdapter: CustomerRecyclerViewAdapter
    private var customersList = mutableListOf<Customer>()

    private val viewModel: CustomersViewModel by lazy {
        ViewModelProviders.of(this,
            Injector.get().viewModelFactory()
        ).get(CustomersViewModel::class.java)

  }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(activity_customers)
        initRecyclerView()
        observeCustomersData()
        observeErrors()
        observeProgress()

    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        customerAdapter = CustomerRecyclerViewAdapter(customersList, object: CustomerRecyclerViewAdapter.OnListItemInteractionListener{
            override fun onItemClicked(item: Customer) {
            }

        })
        recycler_view.adapter = customerAdapter
    }

    private fun observeProgress() {
        viewModel.isLoading.observe(this, Observer {isLoading ->
            if(isLoading)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE

        })
    }

    private fun observeCustomersData() {

        viewModel.customers.observe(this, Observer {
            customersList.addAll(it)
            customerAdapter.notifyDataSetChanged()
        })
    }

    private fun observeErrors() {
        viewModel.error.observe(this, Observer {errorMesage->
            Toast.makeText(this, errorMesage, Toast.LENGTH_LONG).show()
        })
    }


}

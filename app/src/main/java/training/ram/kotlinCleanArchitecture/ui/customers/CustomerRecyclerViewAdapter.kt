package training.ram.kotlinCleanArchitecture.ui.customers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.customer_item.view.*
import training.ram.kotlinCleanArchitecture.data.entities.Customer
import training.ram.kotlin_cleanarchitecture.R

 class CustomerRecyclerViewAdapter(
    private val mValues: List<Customer>,
    private val mListener: OnListItemInteractionListener?
) : RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder>() {

    interface OnListItemInteractionListener {
        fun onItemClicked(item: Customer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        with(holder){
            customerIdView.text = holder.mItem.id.toString()
            fnameView.text = holder.mItem.customerFirstName
            lnameView.text = holder.mItem.customerLastName

        }
        with(holder.mView){
            tag = holder.mItem
            setOnClickListener{
                mListener?.onItemClicked(holder.mItem)
            }
        }
    }

    override fun getItemCount() = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val customerIdView: TextView = mView.customer_id
        val fnameView: TextView = mView.customer_fname
        val lnameView: TextView = mView.custom_lname
        lateinit var mItem: Customer


        override fun toString(): String {
            return super.toString() + " '" + fnameView.text + "'"
        }
    }
}

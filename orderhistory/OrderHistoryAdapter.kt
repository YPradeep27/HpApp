package com.hellopharmacy.views.fragments.orderhistory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.hellopharmacy.R
import com.hellopharmacy.databinding.OrderHistoryItemLayoutBinding
import com.hellopharmacy.views.activities.orderdetails.OrderDetailsActivity


class OrderHistoryAdapter(var mContext: Context, var mList : MutableList<OrderHistoryPojo.OrderArray>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
            val binderObject = DataBindingUtil.inflate<OrderHistoryItemLayoutBinding>(LayoutInflater.from(parent.context), R.layout.order_history_item_layout, parent, false)
            return ViewHolder(binderObject)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int)
    {

        if(viewHolder is ViewHolder)
        {

            viewHolder.bindingObj.binderObj = mList[i]
            viewHolder.bindingObj.executePendingBindings()

            if(mList[i].track.equals("1"))
            {
                viewHolder.bindingObj.orderStatusTxt.text = "Order Placed"
            }
            else if(mList[i].track.equals("2"))
            {
                viewHolder.bindingObj.orderStatusTxt.text = "Paid"

            }
            else if(mList[i].track.equals("3"))
            {
                viewHolder.bindingObj.orderStatusTxt.text = "Processed"
            }
            else if(mList[i].track.equals("4"))
            {
                viewHolder.bindingObj.orderStatusTxt.text = "Shipped"
            }
            else
            {
                viewHolder.bindingObj.orderStatusTxt.text = "Delivered"

            }

            viewHolder.itemView.setOnClickListener {

                val gson = Gson()
                val data = gson.toJson(mList[i])
                val bundle = Bundle()

                bundle.putString("data" , data)
                mContext.startActivity(Intent(mContext , OrderDetailsActivity::class.java).putExtras(bundle))

            }
        }
    }

    override fun getItemCount(): Int
    {
        return mList.size
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class ViewHolder(var bindingObj: OrderHistoryItemLayoutBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(bindingObj.root)

}
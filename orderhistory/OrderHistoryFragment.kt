package com.hellopharmacy.views.fragments.orderhistory

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellopharmacy.R
import com.hellopharmacy.databinding.FragmentOrderHistoryBinding
import com.hellopharmacy.utilities.Constants
import com.hellopharmacy.utilities.common.BaseFragment
import com.hellopharmacy.utilities.customdialogs.CustomDialog
import com.hellopharmacy.utilities.customdialogs.DialogListenerInterface
import com.hellopharmacy.utilities.extensions.isNetworkActive
import com.hellopharmacy.viewmodels.OrdersViewModel
import kotlinx.android.synthetic.main.progress_layout.*


class OrderHistoryFragment : BaseFragment()
{

    lateinit var mViewModel : OrdersViewModel
    lateinit var bindingObj : FragmentOrderHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        bindingObj = DataBindingUtil.inflate(inflater, R.layout.fragment_order_history, container, false)
        return bindingObj.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        initializations()
        progressLoaderObserver()
        apiObserver()
        apiCalling()
    }

    private fun initializations()
    {
        mViewModel = ViewModelProviders.of(this , viewModelFactory).get(OrdersViewModel::class.java)

        val gManager = LinearLayoutManager(activity)
        bindingObj.myOrderRecycler.layoutManager = gManager
    }


    fun apiCalling()
    {

        if(activity?.isNetworkActive() == true)
        {
            mViewModel.getOrderHistory(sharedPreferences.getString(Constants.User_id).toString())
        }
        else
        {

            val dialog  = CustomDialog(activity as Activity)
            dialog.setTitle(resources.getString(R.string.no_internet_txt))
            dialog.setMessage(resources.getString(R.string.connection_txt))
            dialog.setPositiveButton("Retry", object : DialogListenerInterface.onPositiveClickListener
            {
                override fun onPositiveClick() {
                    apiCalling()
                }

            })

            dialog.setNegativeButton("Cancel", object :
                DialogListenerInterface.onNegetiveClickListener
            {
                override fun onNegetiveClick() {
                    dialog.dismiss()
                }

            })
            dialog.show()

        }
    }


    fun apiObserver()
    {
        mViewModel.ordersApiResult.observe(viewLifecycleOwner , Observer {

            if(it.status)
            {
                bindingObj.myOrderRecycler.adapter = OrderHistoryAdapter(requireContext() , it.orderArray?.filter { it.carts?.size!=0 }?.toMutableList()!!)
            }
        } )
    }

    fun progressLoaderObserver()
    {
        mViewModel.loader.observe(this , Observer
        {
            if(it)
            {
                aviProgressBar.visibility = View.VISIBLE
            }
            else
                aviProgressBar.visibility = View.GONE

        })
    }


}
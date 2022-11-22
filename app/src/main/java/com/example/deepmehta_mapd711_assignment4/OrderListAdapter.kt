package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.deepmehta_mapd711_assignment4.entity.Order
import com.example.deepmehta_mapd711_assignment4.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderListAdapter(
    private val context: Context,
    private val orderList: List<Order>,
    private val myViewModel: MyViewModel
) : BaseAdapter() {


    override fun getCount(): Int {
        return orderList.size
    }

    override fun getItem(position: Int): Any {
        return orderList[position]
    }

    override fun getItemId(position: Int): Long {
        return orderList.indexOf(getItem(position)).toLong()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.row_design_order, p2, false)

        CoroutineScope(IO).launch {
            val product = myViewModel.getSelectedProduct(context, orderList[position].productId)
            if (product != null) {
                view.findViewById<TextView>(R.id.phoneName).text = product.phoneModel
                view.findViewById<TextView>(R.id.phoneColor).text = product.phoneColor
                view.findViewById<TextView>(R.id.phoneStorage).text = product.storageCapacity
                view.findViewById<TextView>(R.id.phonePrice).text = product.price
            }
        }

        // Convert string to the local date and time
        val orderDate = orderList[position].orderDate
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val orderDateTime = LocalDateTime.parse(orderDate, formatter)

        view.findViewById<TextView>(R.id.orderStatus).text = orderList[position].orderDate
        view.findViewById<TextView>(R.id.orderStatus).text =
            context.getString(R.string.orderStatus) + " " + orderList[position].status

        val cancelBtn = view.findViewById<Button>(R.id.cancelOrderBtn)

        // Check Date validation for 24 Hours
        if (orderList[position].status.equals(context.getString(R.string.cancelled)) || orderDateTime.plusHours(24).isBefore(LocalDateTime.now())) {
            cancelBtn.visibility = GONE
        } else {
            cancelBtn.visibility = VISIBLE
        }

        // click button to cancel the order
        // data will be updated into the database
        cancelBtn.setOnClickListener(View.OnClickListener {
            myViewModel.cancelOrder(context, orderList[position].orderId!!)
        })

        return view
    }

}
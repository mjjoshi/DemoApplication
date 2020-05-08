package com.spaceo.practicaltest.Practical1

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.spaceo.practicaltest.Practical1.Model.ResponseData
import com.spaceo.practicaltest.Practical1.Model.Result
import com.spaceo.practicaltest.R
import com.spaceo.practicaltest.RetrofitClient
import kotlinx.android.synthetic.main.activity_practical1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Practical1Activity : AppCompatActivity(), ResponseDataAdapter.OnItemClickListener {
    private lateinit var mNotificationAdapter: ResponseDataAdapter
    private var notificationList = listOf<Result>()
    private lateinit var mProgressDialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical1)
        showProgress()
        RetrofitClient.instance.getDatas().enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                mProgressDialog.dismiss()
                notificationList = response.body()!!.Result
                mNotificationAdapter = ResponseDataAdapter(this@Practical1Activity, notificationList, this@Practical1Activity)
                recyclerView.layoutManager = LinearLayoutManager(this@Practical1Activity)
                recyclerView.isNestedScrollingEnabled = false
                recyclerView.adapter = mNotificationAdapter
                recyclerView.setPadding(16, 0, 16, 0)
            }

        })

    }


    fun showProgress() {
        mProgressDialog = Dialog(this)
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mProgressDialog.setContentView(R.layout.progress_dialog)
        mProgressDialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mProgressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
    }


    override fun onItemClick(position: Int) {

    }


}


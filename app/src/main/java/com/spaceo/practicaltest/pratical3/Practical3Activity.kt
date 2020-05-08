package com.spaceo.practicaltest.pratical3


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonObject
import com.spaceo.practicaltest.R
import com.spaceo.practicaltest.pratical3.retrofit.RetrofitClientP
import kotlinx.android.synthetic.main.activity_practical1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap


class Practical3Activity : AppCompatActivity(), ResponseDataAdapterP.OnItemClickListener {

    private var notificationList = listOf<Result>()
    private lateinit var mProgressDialog: Dialog
    private lateinit var mResponseAdapter: ResponseDataAdapterP
    private val subCategoryName = ArrayList<String>()
    val hashMap:HashMap<String,String> = HashMap<String,String>() //define empty hashmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical1)

        showProgress()


        val jsonObject = JsonObject()
        jsonObject.addProperty("CompanyID", 1)
        jsonObject.addProperty("MenuID", 0)
        jsonObject.addProperty("SubCategoryID", 0)
        jsonObject.addProperty("ImageSize", "small")
        jsonObject.addProperty("SearchMerchant", "")
        RetrofitClientP.INSTANCE.getDatas(jsonObject).enqueue(object : Callback<ResponsePractical> {
            override fun onFailure(call: Call<ResponsePractical>, t: Throwable) {
                mProgressDialog.dismiss()
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ResponsePractical>,
                response: Response<ResponsePractical>
            ) {
                mProgressDialog.dismiss()
                notificationList = response.body()!!.Result
                subCategoryName.add("All")
                for (i in notificationList.indices){
                    hashMap.put(notificationList[i].SubCategoryID,notificationList[i].SubCategoryName)
                    Log.d("resultingg",hashMap.toString())
                    subCategoryName.add(notificationList[i].SubCategoryName)

                }




                val adapter = ArrayAdapter(this@Practical3Activity, android.R.layout.simple_spinner_item, subCategoryName)
                spinner1.adapter= adapter
                spinner1.setSelection(0)
                mResponseAdapter = ResponseDataAdapterP(this@Practical3Activity, notificationList, this@Practical3Activity)
                recyclerView.layoutManager = GridLayoutManager(this@Practical3Activity, 2)
                recyclerView.addItemDecoration(SpacesItemDecoration(2, 30, false))
                recyclerView.isNestedScrollingEnabled = false
                recyclerView.adapter = mResponseAdapter
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




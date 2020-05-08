package com.spaceo.practicaltest.Pratical2

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.spaceo.practicaltest.Practical1.Model.ResponseData
import com.spaceo.practicaltest.Practical1.Model.Result
import com.spaceo.practicaltest.R
import com.spaceo.practicaltest.RetrofitClient
import kotlinx.android.synthetic.main.activity_practical2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Practical2Activity : AppCompatActivity() {

    private var notificationList = listOf<Result>()
    private lateinit var mProgressDialog: Dialog

    private lateinit var adapter:ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical2)
        showProgress()
        RetrofitClient.instance.getDatas().enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                mProgressDialog.dismiss()
                notificationList = response.body()!!.Result
                adapter = ViewPagerAdapter(getSupportFragmentManager(), notificationList as ArrayList<Result>)

                vpPager.setAdapter(adapter);
               // adapter.addFragment()

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


    inner class ViewPagerAdapter(fragmentManager: FragmentManager, val movie: ArrayList<Result>):FragmentPagerAdapter(fragmentManager) {

        private val fragments: ArrayList<Fragment> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragments.get(position);
        }
        override fun getCount(): Int {
            return fragments.size
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return movie[position].StoreName
        }
        fun addFragment(fragment: Fragment?, tabTitle: Result?) {
            fragments.add(fragment!!)
            //tabTitles.add(tabTitle!!);

        }
    }


}




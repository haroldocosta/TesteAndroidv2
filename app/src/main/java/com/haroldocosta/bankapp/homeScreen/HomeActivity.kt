package com.haroldocosta.bankapp.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.haroldocosta.bankapp.AuthPreferences
import com.haroldocosta.bankapp.R
import com.haroldocosta.bankapp.StatementViewModel
import com.haroldocosta.bankapp.loginScreen.UserAccount
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

interface HomeActivityInput {
    fun displayHomeMetaData(viewModel: HomeViewModel)
}

//interface HomeRouterOutput {
//    ArrayList<StatementViewModel> listOfVMStatements = null;
//     HomeRouter router = null;
//}

class HomeActivity : AppCompatActivity(), HomeActivityInput {
    var listOfVMStatements: List<StatementRaw>? = null
    var output: HomeInteractorInput? = null
    var router: HomeRouter? = null
    var user: UserAccount = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar!!.hide()
        HomeConfigurator.INSTANCE.configure(this)
        fetchMetaData()
        createStatementListView()
        feedHeaderView()
    }

    fun fetchMetaData() { // create Request and set the needed input
        user = AuthPreferences(baseContext).getUserAccount()
        Log.d(TAG, "fetchMetaData${user.userId.toString()}")
        val homeRequest = HomeRequest(user.userId)
        // Call the output to fetch the data
        output!!.fetchHomeMetaData(homeRequest)
    }

    fun feedHeaderView() {

    }

    private fun createStatementListView() {
        val listView = homeListView as ListView
        Log.d(TAG, "createStatementListView")
        listView.adapter = StatementListAdapter()

        listView.isClickable = false
    }

    override fun displayHomeMetaData(viewModel: HomeViewModel) {
        Log.e(TAG, "displayHomeMetaData() called with: viewModel = [$viewModel]")
        listOfVMStatements = viewModel.statementList
//        fetchMetaData()
        createStatementListView()
    }

    private inner class StatementListAdapter internal constructor() : BaseAdapter() {
        private val layoutInflater: LayoutInflater
        override fun getCount(): Int {
            Log.d(TAG,"getCount")
            if(listOfVMStatements != null){
                Log.d(TAG,"if${listOfVMStatements}")
                return listOfVMStatements!!.size
            }
            else {
                Log.d(TAG,"else")
                return 0
            }
        }

        override fun getItem(position: Int): Any {
            Log.d(TAG,"getItem")
            return listOfVMStatements!![position]
        }

        override fun getItemId(position: Int): Long {
            Log.d(TAG,"getItemId")
            return position.toLong()
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View? {
            Log.d(TAG,"getView")
            var convertView = convertView
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.home_statement_item, null)
                val viewHolder = ViewHolder()
                viewHolder.homeStatementItemTitle = convertView.findViewById<View>(R.id.homeStatementItemTitle) as TextView
                viewHolder.homeStatementItemDate = convertView.findViewById<View>(R.id.homeStatementItemDate) as TextView
                viewHolder.homeStatementItemDesc = convertView.findViewById<View>(R.id.homeStatementItemDesc) as TextView
                viewHolder.homeStatementItemValue = convertView.findViewById<View>(R.id.homeStatementItemValue) as TextView
                convertView.tag = viewHolder
            }
            val viewHolder = convertView?.tag as ViewHolder

            viewHolder.homeStatementItemTitle?.setText(listOfVMStatements!![position].title)
            viewHolder.homeStatementItemDate?.setText(listOfVMStatements!![position].date)
            viewHolder.homeStatementItemDesc?.setText(listOfVMStatements!![position].desc)
            viewHolder.homeStatementItemValue?.setText(listOfVMStatements!![position].value.toString())
            Log.d(TAG,"getView ${convertView}")
            return convertView
        }

        init {
            layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
    }

    internal inner class ViewHolder {
        var homeStatementItemTitle: TextView? = null
        var homeStatementItemDate: TextView? = null
        var homeStatementItemDesc: TextView? = null
        var homeStatementItemValue: TextView? = null
    }

    companion object {
        var TAG = HomeActivity::class.java.simpleName
    }
}

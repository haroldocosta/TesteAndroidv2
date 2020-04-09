package com.haroldocosta.bankapp.homeScreen

import com.haroldocosta.bankapp.R
import com.haroldocosta.bankapp.utils.BaseRecyclerViewAdapter
import com.haroldocosta.bankapp.utils.extensions.dateToFormatBr
import com.haroldocosta.bankapp.utils.extensions.formatCurrency
import kotlinx.android.synthetic.main.home_statement_item.view.*

class StatementsAdapter(statements: List<Statement>) : BaseRecyclerViewAdapter<Statement>(
    items = statements.toMutableList(),
    layoutResId = R.layout.home_statement_item,
    bindView = { view, item ->
        view.homeStatementItemTitle?.text = item.title
        view.homeStatementItemDesc?.text = item.desc
        view.homeStatementItemDate?.text = item.date.dateToFormatBr()
        view.homeStatementItemValue?.text = item.value.formatCurrency()
    }
)
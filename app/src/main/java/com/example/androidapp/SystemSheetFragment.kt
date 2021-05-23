package com.example.androidapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapp.model.CardInfo
import com.example.androidapp.model.DataModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SystemSheetFragment : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "SystemSheetFragment"
        fun newInstance() =
            SystemSheetFragment()
    }

    lateinit var acceptButton: Button
    lateinit var declineButton: Button
    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_system_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        setListenters()

        recyclerView.adapter = SystemAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initViews(view: View) {
        acceptButton = view.findViewById(R.id.accept_system_filters_button)
        declineButton = view.findViewById(R.id.decline_system_filters_button)
        recyclerView = view.findViewById(R.id.system_sheet_recycler_view)
    }

    private fun setListenters() {
        acceptButton.setOnClickListener {
            if (DataModel.extList.size != 0) {
                val a = ArrayList<CardInfo>().toMutableList()
                for (card in DataModel.cardList) {
                    if (DataModel.extList.contains(card.extsysname))
                        a.add(card)
                }
                (activity as MainActivity).adapter?.items = a
                (activity as MainActivity).adapter?.notifyDataSetChanged()

            }
            dismiss()
        }
        declineButton.setOnClickListener {
            DataModel.extList.clear()
            (activity as MainActivity).adapter?.items = DataModel.cardList
            (activity as MainActivity).adapter?.notifyDataSetChanged()
            dismiss()
        }
    }

    override fun onDetach() {
        DataModel.extList.clear()
        super.onDetach()
    }

    class SystemAdapter : RecyclerView.Adapter<SystemAdapter.SystemViewHolder>() {
        inner class SystemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val systemNameTextView: TextView = itemView.findViewById(R.id.system_name_text_view)
            val systemCheckBox: CheckBox = itemView.findViewById(R.id.system_checkbox)

            fun bind(cardInfo: CardInfo) {
                systemNameTextView.text = cardInfo.extsysname
                systemCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    DataModel.addChecked(cardInfo.extsysname, isChecked)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_system_sheet, parent, false)
            return SystemViewHolder(view)
        }

        override fun onBindViewHolder(holder: SystemViewHolder, position: Int) {
            holder.bind(DataModel.cardList[position])
        }

        override fun getItemCount(): Int {
            return DataModel.cardList.size
        }
    }
}
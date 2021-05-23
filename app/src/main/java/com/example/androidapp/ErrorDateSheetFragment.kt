package com.example.androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.w3c.dom.Text

class ErrorDateSheetFragment: BottomSheetDialogFragment() {
    companion object {
        const val TAG = "ErrorDateSheetFragment"
        fun newInstance() =
            ErrorDateSheetFragment()
    }
    lateinit var acceptButton: Button
    lateinit var declineButton: Button
    lateinit var dateBegin: TextView
    lateinit var dateEnd: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_error_date_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        setListenters()
    }

    private fun initViews(view: View) {
        acceptButton = view.findViewById(R.id.accept_error_filters_button)
        declineButton = view.findViewById(R.id.decline_error_filters_button)
        dateBegin = view.findViewById(R.id.date_begin_text_view)
        dateBegin.setOnClickListener {
            Toast.makeText(activity, "Пока не готово!", Toast.LENGTH_LONG).show()
        }
        dateEnd = view.findViewById(R.id.date_end_text_view)
        dateEnd.setOnClickListener {
            Toast.makeText(activity, "Пока не готово!", Toast.LENGTH_LONG).show()
        }
    }

    private fun setListenters() {
        acceptButton.setOnClickListener {
            dismiss()
        }
        declineButton.setOnClickListener {
            dismiss()
        }
    }
}

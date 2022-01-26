package com.example.hw5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {
    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    private lateinit var saveChangesButtonClickListener: SaveChangesButtonClickListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText: EditText = view.findViewById(R.id.nameEditText)
        val surnameEditText: EditText = view.findViewById(R.id.surnameEditText)
        val phoneEditText: EditText = view.findViewById(R.id.phoneEditText)
        val saveChangesButton: Button = view.findViewById(R.id.saveChangesButton)

        nameEditText.setText(contactName)
        surnameEditText.setText(contactSurname)
        phoneEditText.setText(contactPhone)

        saveChangesButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            saveChangesButtonClickListener.saveChangesButtonClicked(contactId, name, surname, phone)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SaveChangesButtonClickListener) {
            saveChangesButtonClickListener = context
        } else {
            throw ClassCastException("$context must implement SaveChangesButtonClickListener")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(contactId: Int,
                        contactName: String,
                        contactSurname: String,
                        contactPhone: String) =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTACT_ID, contactId)
                    putString(CONTACT_NAME, contactName)
                    putString(CONTACT_SURNAME, contactSurname)
                    putString(CONTACT_PHONE, contactPhone)
                }
            }
    }
}
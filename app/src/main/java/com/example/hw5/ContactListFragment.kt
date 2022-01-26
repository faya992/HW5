package com.example.hw5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hw5.databinding.FragmentContactListBinding

private lateinit var binding: FragmentContactListBinding
private const val CONTACT_ID = "contactId"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_SURNAME = "contactSurname"
private const val CONTACT_PHONE = "contactPhone"

private var contacts = arrayOf(
    Contact(1, "Vladimir", "Putin", "71112223344"),
    Contact(2, "Alexander", "Lukashenko", "375111223344"),
    Contact(3, "Nursultan", "Nazarbayev", "74443332211")
)

class ContactListFragment : Fragment() {

    private val contactId: Int by lazy { requireArguments().getInt(CONTACT_ID, 0) }
    private val contactName: String by lazy { requireArguments().getString(CONTACT_NAME, "") }
    private val contactSurname: String by lazy { requireArguments().getString(CONTACT_SURNAME, "") }
    private val contactPhone: String by lazy { requireArguments().getString(CONTACT_PHONE, "") }
    private lateinit var contactClickedListener: ContactClickedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater, container, false)


        binding.contactN1TextView.text = contacts[0].name
        binding.contactN2TextView.text = contacts[1].name
        binding.contactN3TextView.text = contacts[2].name

        if (arguments?.isEmpty == false) {
            when (contactId) {
                1 -> {
                    val contact = contacts[0]
                    contact.name = contactName
                    contact.surname = contactSurname
                    contact.phone = contactPhone
                    binding.contactN1TextView.text = contactName
                }
                2 -> {
                    val contact = contacts[1]
                    contact.name = contactName
                    contact.surname = contactSurname
                    contact.phone = contactPhone
                    binding.contactN2TextView.text = contactName
                }
                3 -> {
                    val contact = contacts[2]
                    contact.name = contactName
                    contact.surname = contactSurname
                    contact.phone = contactPhone
                    binding.contactN3TextView.text = contactName
                }
            }
        }

        binding.contactN1TextView.setOnClickListener {
            val contact = contacts[0]
            contactClickedListener.contactClicked(
                contact.id,
                contact.name,
                contact.surname,
                contact.phone
            )
        }

        binding.contactN2TextView.setOnClickListener {
            val contact = contacts[1]
            contactClickedListener.contactClicked(
                contact.id,
                contact.name,
                contact.surname,
                contact.phone
            )
        }

        binding.contactN3TextView.setOnClickListener {
            val contact = contacts[2]
            contactClickedListener.contactClicked(
                contact.id,
                contact.name,
                contact.surname,
                contact.phone
            )
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ContactClickedListener) {
            contactClickedListener = context
        } else {
            throw ClassCastException("$context must implement ContactClickedListener")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            contactId: Int?,
            contactName: String?,
            contactSurname: String?,
            contactPhone: String?
        ) =
            ContactListFragment().apply {
                if (contactId != null && contactName != null) {
                    arguments = Bundle().apply {
                        putInt(CONTACT_ID, contactId)
                        putString(CONTACT_NAME, contactName)
                        putString(CONTACT_SURNAME, contactSurname)
                        putString(CONTACT_PHONE, contactPhone)
                    }
                } else {
                    ContactListFragment()
                }
            }
    }
}
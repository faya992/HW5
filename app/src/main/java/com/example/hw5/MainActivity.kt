package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hw5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ContactClickedListener,
    SaveChangesButtonClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().run {
            val contactsListFragment =
                ContactListFragment.newInstance(null, null, null, null)
            replace(R.id.fragmentContainer, contactsListFragment)
            commit()
        }
    }

    override fun contactClicked(
        contactId: Int,
        contactName: String,
        contactSurname: String,
        contactPhone: String
    ) {
        supportFragmentManager.beginTransaction().run {
            val contactDetailsFragment = ContactDetailsFragment.newInstance(
                contactId,
                contactName,
                contactSurname,
                contactPhone
            )
            replace(R.id.fragmentContainer, contactDetailsFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun saveChangesButtonClicked(
        contactId: Int,
        contactName: String,
        contactSurname: String,
        contactPhone: String
    ) {
        supportFragmentManager.beginTransaction().run {
            val contactsListFragment = ContactListFragment.newInstance(
                contactId,
                contactName,
                contactSurname,
                contactPhone
            )
            replace(R.id.fragmentContainer, contactsListFragment)
            commit()
        }
    }
}
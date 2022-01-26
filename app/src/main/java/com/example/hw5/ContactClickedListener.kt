package com.example.hw5

interface ContactClickedListener {
    fun contactClicked(contactId: Int, contactName: String, contactSurname: String, contactPhone: String)
}
package com.example.hw5

interface SaveChangesButtonClickListener {
    fun saveChangesButtonClicked(contactId: Int, contactName: String, contactSurname: String, contactPhone: String)
}
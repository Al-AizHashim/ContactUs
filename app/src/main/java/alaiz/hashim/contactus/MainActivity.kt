package alaiz.hashim.contactus

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.PhoneLookup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var github: Button
    lateinit var location: Button
    lateinit var sendEmail: Button
    lateinit var addContact: Button
    lateinit var facebook: Button
    lateinit var twitter: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        github = findViewById(R.id.githubBtn)
        location = findViewById(R.id.locationBtn)
        sendEmail = findViewById(R.id.Send_emailBtn)
        addContact = findViewById(R.id.add_contactBtn)
        facebook = findViewById(R.id.facebookBtn)
        twitter = findViewById(R.id.twitterBtn)
        github.setOnClickListener {
            github()

        }
        location.setOnClickListener {
            location()

        }
        sendEmail.setOnClickListener {
            sendEmail()

        }

        addContact.setOnClickListener {
            if (contactExists(this, "+967775301780")) {
                callme()

            } else {
                addContact()

            }


        }
        facebook.setOnClickListener {
            facebook()

        }
        twitter.setOnClickListener {
            twitter()

        }

    }

    fun github() {
        val githubIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("https://github.com/Al-AizHashim")

        }
        if (githubIntent.resolveActivity(packageManager) != null) {
            startActivity(githubIntent)
        }
    }

    fun location() {
        val locationIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:13.573603,44.012508")

        }
        if (locationIntent.resolveActivity(packageManager) != null) {
            startActivity(locationIntent)
        }
    }

    fun sendEmail() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("alaiz.hashim@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Email subject")
            putExtra(Intent.EXTRA_TEXT, "Email message text")
        }
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }


    fun addContact() {
        val addContactIntent = Intent().apply {
            action = Intent.ACTION_INSERT
            setType(ContactsContract.Contacts.CONTENT_TYPE)
            putExtra(ContactsContract.Intents.Insert.NAME, "Alaiz")
            putExtra(ContactsContract.Intents.Insert.PHONE, "+967777712344")
            putExtra(ContactsContract.Intents.Insert.EMAIL, "alialia@gmail.com")

        }
        if (addContactIntent.resolveActivity(packageManager) != null) {
            startActivity(addContactIntent)
        }
    }

    fun facebook() {
        val facebookIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("https://www.facebook.com/profile.php?id=100003206864693")
        }
        if (facebookIntent.resolveActivity(packageManager) != null) {
            startActivity(facebookIntent)
        }
    }

    fun twitter() {
        val twitterIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("https://twitter.com/1lfl_?s=09")

        }
        if (twitterIntent.resolveActivity(packageManager) != null) {
            startActivity(twitterIntent)
        }
    }

    fun contactExists(_activity: Activity, number: String?): Boolean {
        return if (number != null) {
            val lookupUri = Uri.withAppendedPath(
                PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number)
            )
            val mPhoneNumberProjection = arrayOf(
                PhoneLookup._ID,
                PhoneLookup.NUMBER,
                PhoneLookup.DISPLAY_NAME
            )
            val cur: Cursor? = _activity.contentResolver
                .query(lookupUri, mPhoneNumberProjection, null, null, null)
            try {
                if (cur != null) {
                    if (cur.moveToFirst()) {
                        return true
                    }
                }
            } finally {
                if (cur != null) cur.close()
            }
            false
        } else {
            false
        }
    } 

    fun callme() {
        val callIntent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:+967775301780")

        }
        if (callIntent.resolveActivity(packageManager) != null) {
            startActivity(callIntent)
        }
    }

}
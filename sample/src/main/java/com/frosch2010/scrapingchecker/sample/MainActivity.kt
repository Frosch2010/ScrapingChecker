package com.frosch2010.scrapingchecker.sample

import ScrapingPermissionService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.frosch2010.scrapingchecker.interfaces.IScrapingPermissionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var urlEditText: EditText
    private lateinit var checkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.textInputEditText)
        checkButton = findViewById(R.id.checkButton)

        checkButton.setOnClickListener {
            val url = urlEditText.text.toString().trim()

            if (url.isNotEmpty()) {

                CoroutineScope(Dispatchers.IO).launch {
                    val service: IScrapingPermissionService = ScrapingPermissionService(emptyList())
                    val result = service.isScrapingAllowed(url)

                    val message = if (result.isAllowed) {
                        getString(R.string.scraping_is_allowed)
                    } else {
                        getString(R.string.scraping_is_not_allowed_reason, result.errorMessage)
                    }

                    withContext(Dispatchers.Main) {
                        showToast(message)
                    }
                }
            } else {
                showToast(getString(R.string.please_enter_a_valid_url))
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
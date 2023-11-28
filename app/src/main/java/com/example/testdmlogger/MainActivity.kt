package com.example.testdmlogger

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testdmlogger.ui.theme.TestDMLoggerTheme
import com.example.testdmlogger.databinding.ActivityMainBinding
import java.io.File
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val URL = "URL_BASE = http://13.54.213.9:8080"
    private lateinit var binding: ActivityMainBinding
    private var sdm_resfile = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val statusText = findViewById<TextView>(R.id.textView2)

        val onButton = findViewById<Button>(R.id.button1)
        onButton.setOnClickListener {
            Log.d(TAG, "Clicked! (on)")
            val path = File(System.getProperty("user.dir"))

            Log.d(TAG, "su -c sh /sdcard/logger_scripts/go.sh".runCommand(path))
            val res = "su -c ls /data/vendor/slog -al".runCommand(path)
            statusText.setText(res)
            statusText.invalidate();

            val sdm_filename = "su -c ls /data/vendor/slog/sbuff_2023*.sdm".runCommand(path)
            var sdm_filename_str = ""
            sdm_filename?.let{
                Log.d(TAG, "first")
                sdm_filename_str = sdm_filename.split('\n')[0]
                Log.d(TAG, "sdm file name:$sdm_filename_str")
            }
            sdm_resfile = "$sdm_filename_str.zip"
            Log.d(TAG, "sdm file name(final):$sdm_resfile")
        }

        val offButton = findViewById<Button>(R.id.button2)
        offButton.setOnClickListener {
            Log.d(TAG, "Clicked! (off)")
            val path = File(System.getProperty("user.dir"))
            Log.d(TAG, "su -c sh /sdcard/logger_scripts/st.sh".runCommand(path))
            Log.d(TAG, "su -c ls -al $sdm_resfile".runCommand(path))
            val res = "su -c ls /data/vendor/slog -al".runCommand(path)
            statusText.text = res
        }

        val uploadButton = findViewById<Button>(R.id.button3)
        uploadButton.setOnClickListener {
            Log.d(TAG, "Clicked! (upload)")
            val path = File(System.getProperty("user.dir"))
            val sdmfilepath = File(sdm_resfile)


        }

    }
}


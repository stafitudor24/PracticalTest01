package ro.pub.cs.systems.eim.practicaltest01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
class PracticalTest01MainActivity : AppCompatActivity() {
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var leftTextView: TextView
    private lateinit var rightTextView: TextView
    private val secondaryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        leftButton = findViewById(R.id.left_button)
        rightButton = findViewById(R.id.right_button)
        leftTextView = findViewById(R.id.left_text_view)
        rightTextView = findViewById(R.id.right_text_view)
        leftButton.setOnClickListener {
            val value = leftTextView.text.toString().toInt()
            leftTextView.text = (value + 1).toString()
        }
        rightButton.setOnClickListener {
            val value = rightTextView.text.toString().toInt()
            rightTextView.text = (value + 1).toString()
        }
        val navigateButton = findViewById<Button>(R.id.navigate_button)
        navigateButton.setOnClickListener {
            val total = leftTextView.text.toString().toInt() + rightTextView.text.toString().toInt()
            val intent = Intent(this, PracticalTest01SecondaryActivity::class.java)
            intent.putExtra("total", total)
            secondaryLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("leftCount", leftTextView.text.toString())
        outState.putString("rightCount", rightTextView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("leftCount")) {
            leftTextView.text = savedInstanceState.getString("leftCount")
        }
        if (savedInstanceState.containsKey("rightCount")) {
            rightTextView.text = savedInstanceState.getString("rightCount")
        }
    }
}
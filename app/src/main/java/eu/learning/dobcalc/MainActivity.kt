package eu.learning.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
      private var selecteddate : TextView? = null
   private var minutess : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selecteddate = findViewById(R.id.selecteddate)
        minutess = findViewById(R.id.minutes)

        val btn : Button = findViewById(R.id.datebtn)

        btn.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,theyear,themonth,thedayofmonth ->

            Toast.makeText(this,"Here Is Your Answer",Toast.LENGTH_LONG).show()
            val theselecteddate ="$thedayofmonth/${themonth + 1}/$theyear"
            selecteddate?.setText(theselecteddate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = sdf.parse(theselecteddate)
           theDate?.let {
               val selectedDateINMinutes = theDate.time / 60000

               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

               currentDate?.let {

                   val currentDateInMinutes = currentDate.time / 60000

                   val differenceInMinutes = currentDateInMinutes - selectedDateINMinutes

                   minutess?.text = differenceInMinutes.toString()
               }

           }


        },
        year,
        month,
        day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}
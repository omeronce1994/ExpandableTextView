package expandabletv.omerco.com.expandabletv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnChangeText).setOnClickListener {
            findViewById<ExpandableTextView>(R.id.text).setText(findViewById<EditText>(R.id.editText).text.toString())
        }
        findViewById<Button>(R.id.btnChangeColor).setOnClickListener {
            findViewById<ExpandableTextView>(R.id.text).setTextColor(resources.getColor(R.color.colorAccent))
        }
        findViewById<Button>(R.id.btnChangeUnderline).setOnClickListener {
            findViewById<ExpandableTextView>(R.id.text).isUnderLineExpandingText = !findViewById<ExpandableTextView>(R.id.text).isUnderLineExpandingText
        }
        findViewById<RecyclerView>(R.id.list).layoutManager = LinearLayoutManager(this)
        val list = ArrayList<String>()
        list.add("This is instagram expandable text view in list item number 1")
        list.add("This is instagram expandable text view in list item number 2")
        list.add("This is instagram expandable text view in list item number 3")
        list.add("This is instagram expandable text view in list item number 4")
        list.add("This is instagram expandable text view in list item number 5")
        list.add("This is instagram expandable text view in list item number 6")
        list.add("This is instagram expandable text view in list item number 7")
        list.add("This is instagram expandable text view in list item number 8")
        list.add("This is instagram expandable text view in list item number 9")
        list.add("This is instagram expandable text view in list item number 10")
        list.add("This is instagram expandable text view in list item number 11")
        list.add("This is instagram expandable text view in list item number 12")
        list.add("This is instagram expandable text view in list item number 13")
        list.add("This is instagram expandable text view in list item number 14")
        list.add("This is instagram expandable text view in list item number 15")
        list.add("This is instagram expandable text view in list item number 16")
        list.add("This is instagram expandable text view in list item number 17")
        val adapter = TextListAdapter(this)
        adapter.setItems(list)
        findViewById<RecyclerView>(R.id.list).adapter=adapter
    }
}

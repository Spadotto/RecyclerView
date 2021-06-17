package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val itemList: ArrayList<Item> = generateList()
    private val adapter = ItemAdapter(itemList, this)
    private var titleTF: String = ""
    private var infoTF: String = ""
    var pos: Int = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        var itemTouchHelper = ItemTouchHelper(SwipeDlete(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()

        binding.textInputEditText.setText(itemList[position].titulo)
        binding.textInputEditText2.setText(itemList[position].info)

        pos = position
    }

    private fun generateList(): ArrayList<Item> {
        val list = ArrayList<Item>()
        val item = Item(R.drawable.ic_baseline_emoji_food_beverage_24, "Chá mate", "Ajuda no controle das diabetes")
        list.add(item)
        val item1 = Item(R.drawable.ic_baseline_emoji_food_beverage_24, "Chá de erva-cidreira", "Bom para aliviar o estresse")
        list.add(item1)
        val item2 = Item(R.drawable.ic_baseline_emoji_food_beverage_24, "Chá de hortelã", "Reduz inchaço")
        list.add(item2)
        val item3 = Item(R.drawable.ic_baseline_emoji_food_beverage_24, "Chá de camomila", "Combate a insonia")
        list.add(item3)
        val item4 = Item(R.drawable.ic_baseline_emoji_food_beverage_24, "Chá de gengibre", "Facilita a digestão")
        list.add(item4)
        return list
    }

    fun insertButton(view: View) {
        titleTF = binding.textInputEditText.text.toString()
        infoTF = binding.textInputEditText2.text.toString()
        if(titleTF.isEmpty()){
            Toast.makeText(this, R.string.itensembranco, Toast.LENGTH_SHORT).show()
        }
        if(infoTF.isEmpty()){
            Toast.makeText(this, R.string.itensembranco, Toast.LENGTH_SHORT).show()
        }else{
            val item = Item(R.drawable.ic_baseline_emoji_food_beverage_24, titleTF, infoTF)
            itemList.add(0, item)
            adapter.notifyItemInserted(0)
        }

    }

    fun editButton(view: View) {
        titleTF = binding.textInputEditText.text.toString()
        infoTF = binding.textInputEditText2.text.toString()
        if(titleTF.isBlank()){
            Toast.makeText(this, R.string.itensembranco, Toast.LENGTH_SHORT).show()
        }
        if(infoTF.isEmpty()){
            Toast.makeText(this, R.string.itensembranco, Toast.LENGTH_SHORT).show()
        }else{
            val item = Item(R.drawable.ic_baseline_local_florist_24, titleTF, infoTF)
            adapter.editItem(pos, item)
        }

    }

}

package com.example.myrecycleview

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var tvHeroes: RecyclerView
    private val list = ArrayList<Hero>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHeroes = findViewById(R.id.rv_heroes)
        tvHeroes.setHasFixedSize(true)
        list.addAll(getListHero())
        showRecyclerList()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> tvHeroes.layoutManager = LinearLayoutManager(this)
            R.id.action_grid -> tvHeroes.layoutManager = GridLayoutManager(this,2)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showRecyclerList() {
        if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            tvHeroes.layoutManager = GridLayoutManager(this, 4)
        }else{
            tvHeroes.layoutManager = LinearLayoutManager(this)
        }
        val lisHeroAdapter = ListHeroAdapter(list)
        tvHeroes.adapter = lisHeroAdapter
        lisHeroAdapter.setOnItemClickCallback(object :ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })

    }

    private fun getListHero(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for(i in dataName.indices){
            val hero = Hero(dataName[i],dataDescription[i],dataPhoto[i])
            listHero.add(hero)
        }
        return listHero


    }
    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this,"You selected" + hero.name, Toast.LENGTH_SHORT).show()
    }


}
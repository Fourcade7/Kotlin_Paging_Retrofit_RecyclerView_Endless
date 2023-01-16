package com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pr7.kotlin_pagination.data.model.Results
import com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless.data.network.RetrofitInstance.getApi
import com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless.databinding.ActivityMainBinding
import com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless.screens.RickandMortyAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    lateinit var rickandmortyadapter: RickandMortyAdapter
    lateinit var binding: ActivityMainBinding
     var arrayList= ArrayList<Results>()
    var pagecounter=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val gridlayoutmanager= GridLayoutManager(this@MainActivity,2)
        binding.recyclerview.layoutManager= gridlayoutmanager
        rickandmortyadapter = RickandMortyAdapter(this@MainActivity,arrayList,1f,window.decorView)
        binding.recyclerview.adapter = rickandmortyadapter
        binding.apply {
            recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var pastvidibleitem=gridlayoutmanager.findLastCompletelyVisibleItemPosition()
                    var totalitemcount=gridlayoutmanager.itemCount
                    title="$totalitemcount $pastvidibleitem"
                    if (pastvidibleitem==totalitemcount-1){
                        title="$totalitemcount $pastvidibleitem End Game"
                        gorequest(pagecounter)
                    }
                }
            })
        }
        gorequest(pagecounter)

    }

    fun gorequest(page:Int){
        binding.progressbar1.visibility=View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val resmponse= getApi().getAlldata(page).awaitResponse()
                if (resmponse.isSuccessful){
                    binding.progressbar1.visibility=View.GONE
                    rickandmortyadapter.addresults(resmponse.body()!!.results)
                    pagecounter++
                }
            }

        }
    }



}
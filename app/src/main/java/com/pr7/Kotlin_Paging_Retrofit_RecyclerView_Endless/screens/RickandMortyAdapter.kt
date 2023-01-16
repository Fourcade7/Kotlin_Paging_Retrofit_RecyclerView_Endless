package com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless.screens

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless.databinding.RecyclerviewItemBinding
import com.pr7.kotlin_pagination.data.model.Results
import eightbitlab.com.blurview.RenderEffectBlur

class RickandMortyAdapter(
    val context: Context,
    val arraylist: ArrayList<Results>,
    val radius:Float,
    val decorView:View,
):RecyclerView.Adapter<RickandMortyAdapter.RickandMortyViewHolder>() {

    var checknewresult=ArrayList<Results>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickandMortyViewHolder {
        val view= RecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return RickandMortyViewHolder(view)
    }
    override fun onBindViewHolder(holder: RickandMortyViewHolder, position: Int) {

        holder.binding.apply {
            textviewnameepisode.text=arraylist.get(position).name
            Glide.with(context).load(arraylist.get(position).image).into(imageepisode)
            val radius=radius
            val decorView = decorView
            val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
            val windowBackground: Drawable = decorView.getBackground()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                blurview.setupWith(rootView, RenderEffectBlur()) // or RenderEffectBlur
                    .setFrameClearDrawable(windowBackground) // Optional
                    .setBlurRadius(radius)
            }
        }
    }
    override fun getItemCount(): Int =arraylist.size
    class RickandMortyViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)


    fun addresults(newresults:ArrayList<Results>){
        if (checknewresult!=newresults){
            arraylist.addAll(newresults)
            notifyDataSetChanged()
        }
        checknewresult=newresults

    }





}
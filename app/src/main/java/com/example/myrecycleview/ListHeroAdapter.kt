package com.example.myrecycleview

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHeroAdapter(private val listHero: ArrayList<Hero>): RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
    }
/*4. Kelas ListViewHolder digunakan sebagai ViewHolder dalam RecyclerView. ViewHolder adalah wrapper seperti View yang berisi layout untuk setiap item dalam daftar RecyclerView. Di sini lah tempat untuk menginisialisasi setiap komponen pada layout item dengan menggunakan itemView.findViewById. Adapter akan membuat objek ViewHolder seperlunya, serta menetapkan data untuk tampilan tersebut. Proses yang mengatribusikan tampilan ke datanya disebut binding.*/
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_desc)
    }

    /*
    1. Fungsi onCreateViewHolder() digunakan untuk membuat ViewHolder baru yang berisi layout item yang digunakan, dalam hal ini yaitu R.layout.item_row_hero. Metode ini membuat serta menginisialisasi ViewHolder dan View yang akan diatribusikan. Namun, pada fungsi ini tidak bertujuan mengisi konten tampilan, sehingga belum terikat dengan data tertentu.
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero,parent,false)
        return  ListViewHolder(view)
    }
/*3. Fungsi getItemCount() digunakan untuk menetapkan ukuran dari list data yang ingin ditampilkan. Karena kita ingin menampilkan semua data, maka kita menggunakan listHero.size untuk mengetahui jumlah data pada list. */
    override fun getItemCount(): Int = listHero.size

/*2. Fungsi onBindViewHolder() digunakan untuk menetapkan data yang ada ke dalam ViewHolder sesuai dengan posisinya dengan menggunakan listHero[position]. Misalnya, jika RecyclerView yang dibuat bertujuan untuk menampilkan daftar nama, metode tersebut mungkin menemukan nama yang sesuai, kemudian menetapkannya pada widget TextView yang ada dalam ViewHolder.*/
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])}
      val (name, description, photo) = listHero[position]
        Glide.with(holder.itemView.context)
            .load(photo) // url
            .into(holder.imgPhoto) // target
        holder.tvName.text = name
        holder.tvDescription.text = description
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)

    }

}



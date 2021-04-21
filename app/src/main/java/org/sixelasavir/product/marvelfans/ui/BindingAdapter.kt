package org.sixelasavir.product.marvelfans.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sixelasavir.product.marvelfans.*
import org.sixelasavir.product.marvelfans.api.API_QUERY_CHARACTERS_LIMIT
import org.sixelasavir.product.marvelfans.api.API_QUERY_EVENTS_LIMIT
import org.sixelasavir.product.marvelfans.databinding.ItemComicCharacterBinding
import java.text.SimpleDateFormat
import java.util.*

private const val formatFrom = "yyyy-MM-dd HH:mm:ss"
private const val formatTo = "dd 'de' MMMM YYYY"

private const val format = "yyyy-MM-dd'T'HH:mm:ss"

@BindingAdapter("data_characters")
fun dataCharactersRecyclerView(recyclerView: RecyclerView?, characters: List<Character>?) {
    val adapter: CharacterAdapter = recyclerView?.adapter as CharacterAdapter
    if (characters != null && characters.isNotEmpty()) {
        adapter.characters.addAll(characters)
        adapter.notifyItemInserted(characters.size - API_QUERY_CHARACTERS_LIMIT)
    }
}

@BindingAdapter("data_events")
fun dataEventsRecyclerView(recyclerView: RecyclerView?, events: List<Event>?) {
    val adapter: EventAdapter = recyclerView?.adapter as EventAdapter
    if (events != null && events.isNotEmpty()) {
        adapter.events.addAll(events)
        adapter.notifyItemInserted(events.size - API_QUERY_EVENTS_LIMIT)
    }
}

enum class TypeImage(val text: String) {
    STANDARD_MEDIUM("standard_medium")
}

@BindingAdapter("image_url", "image_extension", "type_image")
fun setImageUrl(imageView: ImageView?, url: String?, extension: String?, typeImage: TypeImage?) {
    if (imageView == null || url.isNullOrEmpty() || extension.isNullOrEmpty() || typeImage == null) return

    val image = "${url}/${typeImage.text}.${extension}"
    Glide.with(imageView).load(image).centerCrop()
        .into(imageView)
}

@SuppressLint("SimpleDateFormat", "WeekBasedYear")
@BindingAdapter("date_text")
fun setDateText(textView: TextView?, dateText: String?) {
    if (textView == null || dateText.isNullOrEmpty()) return

    val simpleDateFormatFrom = SimpleDateFormat(formatFrom)
    val date = simpleDateFormatFrom.parse(dateText)

    val simpleDateFormatTo = SimpleDateFormat(formatTo)
    textView.text = simpleDateFormatTo.format(date!!)
}

@BindingAdapter("go_activity")
fun goCharacterDetailActivity(view: View?, character: Character?) {
    if (view == null || character == null) return

    view.setOnClickListener {
        val intent = Intent(it.context, CharacterDetailActivity::class.java).apply {
            putExtra("key_character", character)
        }
        it.context.startActivity(intent)
    }
}

@BindingAdapter("comics")
fun setDataComic(view: LinearLayout, comics: MutableList<Comic>?) {
    if (view == null || comics.isNullOrEmpty()) return

    for (item: Comic in comics) {
        val itemComicCharacterBinding: ItemComicCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(view.context),
            R.layout.item_comic_character,
            view,
            false
        )
        itemComicCharacterBinding.comic = item

        view.addView(itemComicCharacterBinding.root)
    }
}

@BindingAdapter( "date_comic")
fun getDate(textView: TextView?, dates: MutableList<DateComic>?) {
    if (textView == null || dates == null) return

    val calendar = Calendar.getInstance()
    calendar.time = dates.filter { dateComic ->
        "onsaleDate" == dateComic.type
    }.takeIf { it.size != 0 }?.get(0)?.date

    textView.text = calendar.get(Calendar.YEAR).toString()
}

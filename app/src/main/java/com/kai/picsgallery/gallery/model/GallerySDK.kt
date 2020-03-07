package com.kai.picsgallery.gallery.model

import com.kai.picsgallery.helper.NetworkHelper
import org.json.JSONArray

class GallerySDK
{
    fun getPictureList(): MutableList<Picture>
    {
        val list = mutableListOf<Picture>()
        val jsonString = NetworkHelper.getJsonStringData( "https://picsum.photos/list" )
        if( jsonString.startsWith("Error") )
        {
            return list
        }
        val jsonArray = JSONArray( jsonString )
        for( i in 0 until jsonArray.length() )
        {
            val authorData = jsonArray.getJSONObject( i )
            val authorId = authorData.getInt("id")
            val authorName = authorData.getString( "author" )
            val author =
                Picture(authorId, authorName)
            list.add( author )
        }
        return list
    }
}
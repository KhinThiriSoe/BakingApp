package com.khinthirisoe.bakingapp.data.db.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.khinthirisoe.bakingapp.data.db.IngredientsContract.Ingredients
import com.khinthirisoe.bakingapp.data.model.Ingredient

class IngredientsRepository(private val context: Context) {

    fun saveIngredients(ingredientList: ArrayList<Ingredient>) {
        val contentValues = ArrayList<ContentValues>(ingredientList.size)
        for (ingredient in ingredientList) {
            val values = ContentValues(4)
            values.put(Ingredients.COL_QUALITY, ingredient.quantity)
            values.put(Ingredients.COL_MEASURE, ingredient.measure)
            values.put(Ingredients.COL_NAME, ingredient.ingredient)
            contentValues.add(values)
        }
        val id = context.contentResolver.bulkInsert(Ingredients.CONTENT_URI, contentValues.toTypedArray())
        Log.d("message", "bulk insert $id")
    }

    fun removeIngredient() {
        context.contentResolver.delete(
            Ingredients.CONTENT_URI,
            null,
            null
        )
    }

    fun loadIngredient(): ArrayList<Ingredient> {

        val cursor = context.contentResolver.query(Ingredients.CONTENT_URI, null, null, null, null)

        val ingredientList = arrayListOf<Ingredient>()

        when {
            cursor == null -> {
                // error - log some message
            }
            cursor.count < 1 -> {
                Log.d("message", "detail cursor.getCount() < 1")
            }
            else -> {
                cursor.moveToFirst()
                do {
                    val name = cursor.getString(cursor.getColumnIndex(Ingredients.COL_NAME))
                    val quality = cursor.getString(cursor.getColumnIndex(Ingredients.COL_QUALITY))
                    val measure = cursor.getString(cursor.getColumnIndex(Ingredients.COL_MEASURE))

                    val result = Ingredient(name, quality, measure)

                    ingredientList.add(result)

                } while (cursor.moveToNext())
            }

        }
        cursor.close()
        return ingredientList
    }
}
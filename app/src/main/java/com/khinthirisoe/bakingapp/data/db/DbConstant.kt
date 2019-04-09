package com.khinthirisoe.bakingapp.data.db

object DbConstant {

    const val SEPARATOR = "/"
    const val CURLY_BRACE = "(%s)"

    const val TYPE_TEXT = "TEXT"
    const val PRIMARY_KEY = " PRIMARY KEY"
    const val NOT_NULL = " NOT NULL"
    const val UNIQUE = " UNIQUE"
    const val TYPE_PRIMARY_KEY_TEXT_UNIQUE = TYPE_TEXT + PRIMARY_KEY + NOT_NULL + UNIQUE
    const val TYPE_TEXT_NOT_NULL = TYPE_TEXT + NOT_NULL

}

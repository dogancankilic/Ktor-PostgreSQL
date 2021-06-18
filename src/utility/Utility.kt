package com.ktorpostgresample.utility

class Utility {
    fun checkNumber(number: String): Boolean {
        try {
            number.toInt()
            return true
        } catch (e: NumberFormatException) {

        }
        return false
    }
}
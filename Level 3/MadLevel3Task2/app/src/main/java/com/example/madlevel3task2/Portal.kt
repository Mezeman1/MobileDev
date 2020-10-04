package com.example.madlevel3task2

class Portal(
    var title: String,
    var url: String,
) {
    companion object {
        val PORTAL_TITLES = arrayOf(
            "Test 1",
            "Test 2",
            "Test 1",
            "Test 2",
        )

        val PORTAL_URLS = arrayOf(
            "Extra lange tekst om te testen hoe dat wrapped",
            "TEST 2",
            "TEST 1",
            "TEST 2",
        )
    }

}
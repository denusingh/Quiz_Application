package com.rahul.quiz.quiz


class TestScore {


    lateinit var user: String
    lateinit var score: String

    constructor() {

    }

    constructor(user: String, score: String) {

        this.user = user
        this.score = score
    }
}

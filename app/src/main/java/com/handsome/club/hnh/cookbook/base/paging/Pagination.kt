package com.handsome.club.hnh.cookbook.base.paging

class Pagination(
    private val currentPage: Int = 0,
    private val limit: Int = 10,
) {
    private val startingPage: Int = currentPage

    val offset: Int
        get() = currentPage * limit

    fun nextPage() = Pagination(currentPage + 1, limit)

    fun refresh() = Pagination(startingPage, limit)

}

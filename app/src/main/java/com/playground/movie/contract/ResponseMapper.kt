package com.playground.movie.contract

/**
 * @Details ResponseMapper: It helps to create the Model from Existing response source.
 * This way we can prevent any NPE due to service response changes.
 * @Author Roshan Bhagat
 */
interface ResponseMapper<Response, UIModel> {

    fun responseToUIModel(response: Response): UIModel
}
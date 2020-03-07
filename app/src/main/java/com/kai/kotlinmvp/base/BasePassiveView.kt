package com.kai.kotlinmvp.base

/**
 * This is an observable extension of BaseView which allows any controller/presenter to
 * observe events from the view.
 * This enables the view to be completely passive/dumb/reusable without being directly coupled
 * with the controller/presenter through composition.
 */
open class BasePassiveView<Listener> : BaseView() {

    private val _mListeners = mutableSetOf<Listener>()
    val mListeners: Set<Listener>
        get() = _mListeners

    fun registerListener(listener: Listener) {
        _mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        _mListeners.remove(listener)
    }

}
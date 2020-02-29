package com.kai.kotlinmvp.base

import java.util.*

/**
 * This is an observable extension of BaseView which allows any controller/presenter to
 * observe events from the view.
 * This enables the view to be completely passive/dumb/reusable without being directly coupled
 * with the controller/presenter through composition.
 */
open class BasePassiveView<Listener> : BaseView() {

    private val mListeners = mutableSetOf<Listener>()

    fun registerListener(listener: Listener) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        mListeners.remove(listener)
    }

    fun getListeners(): Set<Listener> {
        return Collections.unmodifiableSet(mListeners)
    }
}
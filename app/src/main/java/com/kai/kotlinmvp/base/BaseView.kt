package com.kai.kotlinmvp.base

import android.content.Context
import android.view.View

/**
 * Provides base infrastructure needed to decouple view inflation logic from Activity/Fragment
 * so that the view can be made passive. See BasePassiveView for implementation.
 */
open class BaseView() {

    private lateinit var mRootView: View

    protected fun getContext(): Context {
        return getRootView().context
    }

    protected fun setRootView(view: View){
        mRootView = view
    }

    protected fun <T : View> findViewById(id: Int): T {
        return mRootView.findViewById(id)
    }

    fun getRootView(): View {
        return mRootView
    }

}
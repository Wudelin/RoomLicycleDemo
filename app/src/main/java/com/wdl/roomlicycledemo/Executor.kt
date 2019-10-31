package com.wdl.roomlicycledemo

import java.util.concurrent.Executors

/**
 * Create by: wdl at 2019/10/31 17:00
 * 线程池管理类
 */

private val IO_EXECUTORS = Executors.newSingleThreadExecutor()

fun ioThread(f: () -> Unit) {
    IO_EXECUTORS.execute(f)
}
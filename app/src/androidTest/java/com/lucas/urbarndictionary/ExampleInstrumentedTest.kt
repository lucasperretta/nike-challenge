package com.lucas.urbarndictionary

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucas.urbarndictionary.repositories.IndexRepository
import com.lucas.urbarndictionary.viewmodels.IndexActivityViewModel
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 * val appContext = InstrumentationRegistry.getInstrumentation().targetContext
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test fun indexRepository_jsonRequestTest() {
        val latch = CountDownLatch(1)
        IndexRepository.getData("test", IndexActivityViewModel.ThumbsFilter.UP) { list ->
            assert(list != null)
            assert(list!!.isNotEmpty())
            latch.countDown()
        }
        latch.await()
    }

}
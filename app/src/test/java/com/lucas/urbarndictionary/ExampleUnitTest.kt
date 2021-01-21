package com.lucas.urbarndictionary

import com.lucas.urbarndictionary.extensions.parseDate
import com.lucas.urbarndictionary.extensions.toStringFormat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test fun dateParsingAndFormatting() {
        val stringDate = "2020-01-21 10:00:00"
        val format = "yyyy-MM-dd hh:mm:ss"

        val date = stringDate.parseDate(format)

        assert(date != null)
        assert(date!!.toStringFormat(format) == stringDate)
    }

}
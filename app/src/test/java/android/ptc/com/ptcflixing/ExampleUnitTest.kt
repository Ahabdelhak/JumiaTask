package android.ptc.com.ptcflixing

import android.ptc.com.ptcflixing.util.priceFormatter
import android.ptc.com.ptcflixing.util.savingFormatter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun stringFormatter_priceFormatter() {
        assertEquals("$ 22,999", 22999.priceFormatter("$", ","))
    }

    @Test
    fun stringFormatter_savingFormatter() {
        assertEquals("-45%", 45.savingFormatter())
    }
}
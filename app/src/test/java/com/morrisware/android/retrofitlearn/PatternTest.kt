package com.morrisware.android.retrofitlearn

import org.junit.*
import retrofit2.http.Query
import java.lang.Exception
import java.util.regex.Matcher
import java.util.regex.Pattern

class PatternTest {

    private val PARAM = "[a-zA-Z][a-zA-Z0-9_-]*"
    private val PARAM_URL_REGEX = Pattern.compile("\\{($PARAM)\\}")

    //    private val input = "name={asdasd}&date={data}"
    private val input = "{asdasd}&date={data}"
//    private val input = "{asdasd}{data}"

    @Test
    fun testURLPattern() {
        val matcher = PARAM_URL_REGEX.matcher(input)
        System.out.println("pattern input: $input")
        System.out.println("pattern: ${matcher.pattern()}")
        // 全匹配
        System.out.println("matches: ${matcher.matches()}")
        // 不需要全匹配，只要最前面的字符串符合规则
        System.out.println("lookingAt: ${matcher.lookingAt()}")
        // 不需要全匹配，并且可以在任意位置
        System.out.println("find: ${matcher.find()}")
    }

    @Test
    fun testMatches() {
        val matcher = PARAM_URL_REGEX.matcher(input)
        matcher.matches()
        printGroup("matches", matcher)
    }

    @Test
    fun testLookingAt() {
        val matcher = PARAM_URL_REGEX.matcher(input)
        matcher.lookingAt()
        printGroup("lookingAt", matcher)
    }

    @Test
    fun testFind() {
        val matcher = PARAM_URL_REGEX.matcher(input)
        while (matcher.find()) {
            printGroup("find", matcher)
        }
    }

    @Test
    fun testFind1() {
        val pattern = Pattern.compile("(\\{$PARAM\\})($PARAM)")
        val matcher = pattern.matcher("{asda}asd{dasdsa}asda{dasdsa}")
        while (matcher.find()) {
            printGroup("find", matcher)
        }
    }

    @Test
    fun parsePathParameters() {
        val path: String = "search/{path}"
        val matcher = PARAM_URL_REGEX.matcher(path)
        while (matcher.find()) {
            System.out.println(matcher.group(0))
            System.out.println(matcher.group(1))
        }
    }

    @Test
    fun test1() {
        val pattern = Pattern.compile("\\{((\\d+))\\}")
        val matcher = pattern.matcher("{123}")
        if (matcher.find()) {
            System.out.println(matcher.group(0))
            System.out.println(matcher.group(1))
            System.out.println(matcher.group(2))
        }
    }

    private fun printGroup(type: String, matcher: Matcher) {
        try {
            System.out.println("$type groupCount: ${matcher.groupCount()}")
            for (i in 0 until matcher.groupCount()) {
                System.out.println("$type group[$i]=${matcher.group(i)}")
                System.out.println("$type start: ${matcher.start(i)}")
                System.out.println("$type end: ${matcher.end(i)}")
            }
        } catch (e: Exception) {
            System.out.println("$type fail: ${e.message}")
        }

    }

}
package com.example.fakenews.data.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SourceXXTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testSource: SourceXX

    @Before
    fun setup() {
        testSource = SourceXX("testing", "testing")
    }

    @Test
    fun testType() {
        assertThat(testSource).isInstanceOf(SourceXX::class.java)
        assertThat(testSource).isNotNull()
    }

    @Test
    fun testid() {
        assertThat(testSource.id).isEqualTo("testing")
    }

}
package com.example.bloodlife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest{
    class BMI{

        private com.example.bloodlife.BMI bmi;

        //To initialize bmi before starting the test
        @Before
        public void init(){
            bmi = new com.example.bloodlife.BMI ( );
        }
        @Test
        public void CalculateBMI(){
            float expectedValue = 23.3068f;
            assertEquals(23.3068, 65 / 167*167 );

        }
    }
    @Test
    public void addition_isCorrect(){
        assertEquals ( 4, 2 + 2 );
    }
}
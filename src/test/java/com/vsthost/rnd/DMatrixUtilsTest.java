/*
 * Copyright (c) 2015 Vehbi Sinan Tunalioglu <vst@vsthost.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vsthost.rnd;

import com.vsthost.rnd.commons.math.ext.linear.DMatrixUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for EMatrixUtils.
 */
public class DMatrixUtilsTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DMatrixUtilsTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DMatrixUtilsTest.class );
    }

    public void testSum () {
        this.assertEquals(6.0, DMatrixUtils.sum(new double[] {1, 2, 3}));
        this.assertEquals(-6.0, DMatrixUtils.sum(new double[] {-1, -2, -3}));
    }

    public void testSumOfAbsolutes () {
        this.assertEquals(6.0, DMatrixUtils.sumOfAbsolutes(new double[]{1, 2, 3}));
        this.assertEquals(6.0, DMatrixUtils.sumOfAbsolutes(new double[]{-1, -2, -3}));
    }

    public void testMean () {
        this.assertEquals(2.0, DMatrixUtils.mean(new double[]{1, 2, 3}));
        this.assertEquals(-2.0, DMatrixUtils.mean(new double[]{-1, -2, -3}));
    }

    public void testMedian () {
        this.assertEquals(2.0, DMatrixUtils.median(new double[]{1, 2, 3}));
        this.assertEquals(-2.0, DMatrixUtils.median(new double[]{-1, -2, -3}));
        this.assertEquals(2.5, DMatrixUtils.median(new double[]{1, 2, 3, 4}));
        this.assertEquals(-2.5, DMatrixUtils.median(new double[]{-1, -2, -3, -4}));
    }

    public void testSequence () {
        // Get the sequence:
        double[] sequence = DMatrixUtils.sequence(0, 1, 1);
        this.assertEquals(1, sequence.length);
        this.assertEquals(0.0, sequence[0]);

        sequence = DMatrixUtils.sequence(0, 1, 2);
        this.assertEquals(2, sequence.length);
        this.assertEquals(0.0, sequence[0]);
        this.assertEquals(1.0, sequence[1]);

        sequence = DMatrixUtils.sequence(0, 1, 3);
        this.assertEquals(3, sequence.length);
        this.assertEquals(0.0, sequence[0], 0.0001);
        this.assertEquals(0.5, sequence[1], 0.0001);
        this.assertEquals(1.0, sequence[2], 0.0001);

        sequence = DMatrixUtils.sequence(0, 1, 4);
        this.assertEquals(4, sequence.length);
        this.assertEquals(0.0000, sequence[0]);
        this.assertEquals(0.3333, sequence[1], 0.0001);
        this.assertEquals(0.6666, sequence[2], 0.0001);
        this.assertEquals(0.9999, sequence[3], 0.0001);

        sequence = DMatrixUtils.sequence(1.2, 2, 2);
        this.assertEquals(2, sequence.length);
        this.assertEquals(1.2, sequence[0]);
        this.assertEquals(2.0, sequence[1]);

        sequence = DMatrixUtils.sequence(1.2, 2, 3);
        this.assertEquals(3, sequence.length);
        this.assertEquals(1.2, sequence[0]);
        this.assertEquals(1.6, sequence[1]);
        this.assertEquals(2.0, sequence[2]);
    }
}

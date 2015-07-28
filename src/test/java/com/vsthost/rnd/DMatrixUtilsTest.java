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
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

import java.math.BigDecimal;
import java.util.stream.IntStream;

/**
 * Unit tests for EMatrixUtils.
 */
public class DMatrixUtilsTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DMatrixUtilsTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DMatrixUtilsTest.class);
    }

    /**
     * Test the sum of arrays.
     */
    public void testSum() {
        this.assertEquals(6.0, DMatrixUtils.sum(new double[]{1, 2, 3}));
        this.assertEquals(-6.0, DMatrixUtils.sum(new double[]{-1, -2, -3}));
    }

    public void testSumOfAbsolutes() {
        this.assertEquals(6.0, DMatrixUtils.sumOfAbsolutes(new double[]{1, 2, 3}));
        this.assertEquals(6.0, DMatrixUtils.sumOfAbsolutes(new double[]{-1, -2, -3}));
    }

    public void testMean() {
        this.assertEquals(2.0, DMatrixUtils.mean(new double[]{1, 2, 3}));
        this.assertEquals(-2.0, DMatrixUtils.mean(new double[]{-1, -2, -3}));
    }

    public void testMedian() {
        this.assertEquals(2.0, DMatrixUtils.median(new double[]{1, 2, 3}));
        this.assertEquals(-2.0, DMatrixUtils.median(new double[]{-1, -2, -3}));
        this.assertEquals(2.5, DMatrixUtils.median(new double[]{1, 2, 3, 4}));
        this.assertEquals(-2.5, DMatrixUtils.median(new double[]{-1, -2, -3, -4}));
    }

    public void testSequence() {
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


    public void testNearing() {
        this.assertEquals(new BigDecimal("0.000"), DMatrixUtils.roundDownTo(0.0012345, 0.025));
        this.assertEquals(new BigDecimal("0.025"), DMatrixUtils.roundUpTo(0.0012345, 0.025));
        this.assertEquals(new BigDecimal("0.150"), DMatrixUtils.roundDownTo(0.171234, 0.025));
        this.assertEquals(new BigDecimal("0.175"), DMatrixUtils.roundUpTo(0.171234, 0.025));
    }

    public void testClosest() {
        this.assertEquals(new BigDecimal("0.000"), DMatrixUtils.roundToClosest(0.0012345, 0.025));
        this.assertEquals(new BigDecimal("0.175"), DMatrixUtils.roundToClosest(0.171234, 0.025));
        this.assertEquals(new BigDecimal("0.000"), DMatrixUtils.roundToClosest(0.012445, 0.025));
        this.assertEquals(new BigDecimal("0.025"), DMatrixUtils.roundToClosest(0.012645, 0.025));
        this.assertEquals(new BigDecimal("0.150"), DMatrixUtils.roundToClosest(0.161234, 0.025));
    }

    /**
     * Testing ZMBD with predefined args.
     */
    public void testZmbd() {
        // Create a random generator:
        MersenneTwister randomGenerator = new MersenneTwister();

        // Create upper/lower bounds:
        double[] lowerSingle = new double[]{-5.0};
        double[] upperSingle = new double[]{+5.0};
        double[] lowerDouble = new double[]{-4.0, -4.0};
        double[] upperDouble = new double[]{+1.0, +1.0};
        double[] lowerTriple = new double[]{-1.0, -2.0, -3.0};
        double[] upperTriple = new double[]{+1.0, +2.0, +3.0};
        double[] lowerQuad = new double[]{-4.0, -4.0, -4.0, -4.0};
        double[] upperQuad = new double[]{+1.0, +1.0, +1.0, +1.0};
        double[] lowerReal = new double[]{-4.0, -1.0, -1.0, -4.0};
        double[] upperReal = new double[]{+1.0, +5.0, +1.0, +1.0};
        double[] lowerWZero = new double[]{-4.0, -0.000001, -1.0, -4.0};
        double[] upperWZero = new double[]{+1.0, +5.0, +0.0000001, +1.0};
        double[] lowerIllegal = new double[]{-4.0, -1.0, +1.0, -4.0};
        double[] upperIllegal = new double[]{+1.0, +5.0, -1.0, +1.0};

        // Means should always be zero:
        this.assertEquals(0.0, Math.abs(DMatrixUtils.sum(DMatrixUtils.zmdb(lowerSingle, upperSingle, randomGenerator))), 1E-8);
        this.assertEquals(0.0, Math.abs(DMatrixUtils.sum(DMatrixUtils.zmdb(lowerDouble, upperDouble, randomGenerator))), 1E-8);
        this.assertEquals(0.0, Math.abs(DMatrixUtils.sum(DMatrixUtils.zmdb(lowerTriple, upperTriple, randomGenerator))), 1E-8);
        this.assertEquals(0.0, Math.abs(DMatrixUtils.sum(DMatrixUtils.zmdb(lowerQuad, upperQuad, randomGenerator))), 1E-8);
        this.assertEquals(0.0, Math.abs(DMatrixUtils.sum(DMatrixUtils.zmdb(lowerReal, upperReal, randomGenerator))), 1E-8);
        this.assertEquals(0.0, Math.abs(DMatrixUtils.sum(DMatrixUtils.zmdb(lowerWZero, upperWZero, randomGenerator))), 1E-8);

        // Check boundaries:
        this.assertEquals(-1, this.checkBoundaries(DMatrixUtils.zmdb(lowerSingle, upperSingle, randomGenerator), lowerSingle, upperSingle));
        this.assertEquals(-1, this.checkBoundaries(DMatrixUtils.zmdb(lowerDouble, upperDouble, randomGenerator), lowerDouble, upperDouble));
        this.assertEquals(-1, this.checkBoundaries(DMatrixUtils.zmdb(lowerTriple, upperTriple, randomGenerator), lowerTriple, upperTriple));
        this.assertEquals(-1, this.checkBoundaries(DMatrixUtils.zmdb(lowerQuad, upperQuad, randomGenerator), lowerQuad, upperQuad));
        this.assertEquals(-1, this.checkBoundaries(DMatrixUtils.zmdb(lowerReal, upperReal, randomGenerator), lowerReal, upperReal));
        this.assertEquals(-1, this.checkBoundaries(DMatrixUtils.zmdb(lowerWZero, upperWZero, randomGenerator), lowerWZero, upperWZero));

        // Expecting exceptions for dimension mismatch:
        try {
            DMatrixUtils.zmdb(lowerSingle, upperDouble, randomGenerator);
            fail("Dimension mismatch for boundaries must fail.");
        } catch (IllegalArgumentException exception) {
            if (!exception.getMessage().equals("Lower and upper bounds must be of same length.")) {
                fail("Dimension mismatch for boundaries must fail with proper message.");
            }
        }

        // Expecting exceptions for positive lower boundary:
        try {
            DMatrixUtils.zmdb(lowerIllegal, upperReal, randomGenerator);
            fail("Illegal lower bound must fail");
        } catch (IllegalArgumentException exception) {
            if (!exception.getMessage().equals("Lower bounds must be equal to or less than 0.")) {
                fail("Illegal lower bound must fail with proper message.");
            }
        }

        // Expecting exceptions for positive upper boundary:
        try {
            DMatrixUtils.zmdb(lowerReal, upperIllegal, randomGenerator);
            fail("Illegal upper bound must fail");
        } catch (IllegalArgumentException exception) {
            if (!exception.getMessage().equals("Upper bounds must be equal to or greater than 0.")) {
                fail("Illegal upper bound must fail with proper message.");
            }
        }
    }

    /**
     * Testing ZMBD multiple times with predefined args.
     */
    public void testMultipleZMBD () {
        IntStream.range(0, 1000).forEach(e -> this.testZmbd());
    }

    /**
     * Random ZMBD test.
     */
    public void testZmbdRandom() {
        final double[] randLower = new UniformRealDistribution(-0.00002, -0.00001).sample(100);
        final double[] randUpper = new UniformRealDistribution(+0.00001, +0.00002).sample(100);
        final RandomGenerator randomGenerator = new MersenneTwister();
        final double[] zmbd = DMatrixUtils.zmdb(randLower, randUpper, randomGenerator);
        this.assertEquals(-1, this.checkBoundaries(zmbd, randLower, randUpper));
    }

    /**
     * Multiple test for random ZMBD test.
     */
    public void testMultipleZmbdRandom () {
        IntStream.range(0, 1000).forEach(e -> this.testZmbdRandom());
    }

    /**
     * Testing {@link DMatrixUtils#applyIndices(double[], int[])}
     */
    public void testApplyIndices() {
        // Test empty:
        this.assertEquals(0, DMatrixUtils.applyIndices(new double[]{}, new int[]{}).length);

        // Create a vector:
        final double[] vector = new double[] {0, 1, 2, 3};

        // Create sequantial indices:
        final int[] indices = new int[] {3, 2, 1, 0};

        // Apply:
        final double[] appliedVector = DMatrixUtils.applyIndices(vector, indices);

        // Check:
        this.assertEquals(vector[3], appliedVector[0]);
        this.assertEquals(vector[2], appliedVector[1]);
        this.assertEquals(vector[1], appliedVector[2]);
        this.assertEquals(vector[0], appliedVector[3]);
    }

    /**
     * Convenience method to test boundaries.
     *
     * @param values Values to be tested.
     * @param lower Lower boundaries.
     * @param upper Upper boundaries
     * @return -1 if no violation, index of first violation otherwise.
     */
    private int checkBoundaries (double[] values, double[] lower, double[] upper) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] < lower[i] || values[i] > upper[i]) {
                return i;
            }
        }
        return -1;
    }
}
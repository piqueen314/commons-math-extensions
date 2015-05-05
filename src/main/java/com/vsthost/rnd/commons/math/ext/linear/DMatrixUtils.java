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

package com.vsthost.rnd.commons.math.ext.linear;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.MatrixUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class provides utilities for matrices implemented as
 * ordinary arrays of double arrays.
 */
public class DMatrixUtils {
    /**
     * Returns the sum of the vector.
     *
     * @param vector The input vector as a double array
     * @return The sum of the vector
     */
    public static double sum (double[] vector) {
        // Declare and initialize the accumulator:
        double total = 0.0;

        // Iterate over the vector:
        for (int i = 0; i < vector.length; i++) {
            total += vector[i];
        }

        // Done, return:
        return total;
    }

    /**
     * Returns the sum of the absolute values in the vector.
     *
     * @param vector The input vector as a double array
     * @return The sum of the absolute values in the vector
     */
    public static double sumOfAbsolutes (double[] vector) {
        // Declare and initialize the accumulator:
        double total = 0.0;

        // Iterate over the vector:
        for (int i = 0; i < vector.length; i++) {
            total += Math.abs(vector[i]);
        }

        // Done, return:
        return total;
    }

    /**
     * Returns the mean of the vector.
     *
     * @param vector The input vector as a double array
     * @return The mean of the vector
     */
    public static double mean(double[] vector) {
        return sum(vector) / vector.length;
    }

    /**
     * Returns the median of the vector.
     *
     * @param vector The input vector as a double array
     * @return The median of the vector
     */
    public static double median (double[] vector) {
        final double[] sorted = vector.clone();
        Arrays.sort(sorted);
        if (vector.length % 2 == 1) {
            return sorted[vector.length / 2];
        }
        return (sorted[vector.length / 2 - 1] + sorted[vector.length / 2]) / 2;
    }

    /**
     * Generates a sequence of specified length from specified start to specified end.
     *
     * @param start The number to start with.
     * @param end The number to end with.
     * @param length The length of the desired sequence.
     * @return A sequence of length {@code length} starting with {@code start} and ending with {@code end}.
     */
    public static double[] sequence (double start, double end, int length) {
        // Declare and initialize the return value:
        double[] retval = new double[length];

        // Calculate step:
        double step = (end - start) / (length == 1 ? 1 : length - 1);

        // Iterate and fill:
        for (int i = 0; i < length; i++) {
            retval[i] = start + (i * step);
        }

        // Done, return;
        return retval;
    }

    /**
     * Creates and returns an array of length {@code n} with all values set to {@code value}.
     *
     * @param value The value to be filled.
     * @param n The desired length of the array.
     * @return An array of length {@code n} with all values set to {@code value}
     */
    public static double[] repeat (double value, int n) {
        // Initialize the return array:
        double[] retval = new double[n];

        // Fill the array:
        Arrays.fill(retval, value);

        // Done, return the filled array:
        return retval;
    }

    /**
     * Clones the given matrix.
     *
     * @param matrix The matrix to be cloned.
     * @return The cloned matrix.
     */
    public static double[][] cloneMatrix (double[][] matrix) {
        return MatrixUtils.createRealMatrix(matrix).getData();
    }

    /**
     * Creates a new array by selecting those elements marked as true in the predicate array.
     *
     * @param values The array where the elements are going to be selected from.
     * @param predicate The selection mapper.
     * @return The new array with selected items.
     */
    public static String[] selectByPredicate (String[] values, Boolean[] predicate) {
        // Define the filtered list:
        List<String> filtered = new ArrayList<String>();

        // Iterate and populate:
        for (int i = 0; i < predicate.length; i++) {
            if (predicate[i]) {
                filtered.add(values[i]);
            }
        }

        // Save the value:
        return filtered.toArray(new String[0]);
    }

    /**
     * Creates a new array by selecting those elements marked as true in the predicate array.
     *
     * @param values The array where the elements are going to be selected from.
     * @param predicate The selection mapper.
     * @return The new array with selected items.
     */
    public static double[] selectByPredicate (double[] values, boolean[] predicate) {
        // Define the filtered list:
        List<Double> filtered = new ArrayList<>();

        // Iterate and populate:
        for (int i = 0; i < predicate.length; i++) {
            if (predicate[i]) {
                filtered.add(values[i]);
            }
        }

        // Save the value:
        return ArrayUtils.toPrimitive(filtered.toArray(new Double[0]));
    }

    /**
     * Creates a new array by selecting those elements marked as true in the predicate array.
     *
     * @param values The array where the elements are going to be selected from.
     * @param predicate The selection mapper.
     * @return The new array with selected items.
     */
    public static double[] selectByPredicate (double[] values, Boolean[] predicate) {
        return DMatrixUtils.selectByPredicate(values, ArrayUtils.toPrimitive(predicate));
    }

    /**
     * Get the order of the specified elements in descending or ascending order.
     *
     * @param values A vector of double values.
     * @param indices The indices which will be considered for ordering.
     * @param descending Flag indicating if we go descending or not.
     * @return A vector of indices sorted in the provided order.
     */
    public static int[] getOrder(double[] values, int[] indices, boolean descending) {
        // Create an index series:
        Integer[] opIndices = ArrayUtils.toObject(indices);

        // Sort indices:
        Arrays.sort(opIndices, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (descending) {
                    return Double.compare(values[o2], values[o1]);
                } else {
                    return Double.compare(values[o1], values[o2]);
                }
            }
        });

        return ArrayUtils.toPrimitive(opIndices);
    }

    /**
     * Get the order of the elements in descending or ascending order.
     *
     * @param values A vector of double values.
     * @param descending Flag indicating if we go descending or not.
     * @return A vector of indices sorted in the provided order.
     */
    public static int[] getOrder(double[] values, boolean descending) {
        return DMatrixUtils.getOrder(values, IntStream.range(0, values.length).toArray(), descending);
    }

    /**
     * Get the order of the elements in ascending order.
     *
     * @param values A vector of double values.
     * @return A vector of indices sorted in the ascending order.
     */
    public static int[] getOrder(double[] values) {
        return DMatrixUtils.getOrder(values, false);
    }

    /**
     * Returns the DOWN rounded value of the given value for the given steps.
     *
     * @param value The original value to be rounded.
     * @param steps The steps.
     * @return The DOWN rounded value of the given value for the given steps.
     */
    public static BigDecimal roundDownTo(double value, double steps) {
        final BigDecimal bValue = BigDecimal.valueOf(value);
        final BigDecimal bSteps = BigDecimal.valueOf(steps);

        if (bSteps == BigDecimal.ZERO) {
            return bValue;
        } else {
            return bValue.divide(bSteps, 0, RoundingMode.FLOOR).multiply(bSteps);
        }
    }

    /**
     * Returns the UP rounded value of the given value for the given steps.
     *
     * @param value The original value to be rounded.
     * @param steps The steps.
     * @return The UP rounded value of the given value for the given steps.
     */
    public static BigDecimal roundUpTo(double value, double steps) {
        final BigDecimal bValue = BigDecimal.valueOf(value);
        final BigDecimal bSteps = BigDecimal.valueOf(steps);

        if (bSteps == BigDecimal.ZERO) {
            return bValue;
        } else {
            return bValue.divide(bSteps, 0, RoundingMode.CEILING).multiply(bSteps);
        }
    }

    /**
     * Returns the closest rounded value of the given value for the given steps.
     *
     * @param value The original value to be rounded.
     * @param steps The steps.
     * @return The closest rounded value of the given value for the given steps.
     */
    public static BigDecimal roundToClosest(double value, double steps) {
        final BigDecimal down = DMatrixUtils.roundDownTo(value, steps);
        final BigDecimal up = DMatrixUtils.roundUpTo(value, steps);
        final BigDecimal orig = new BigDecimal(String.valueOf(value));
        if (orig.subtract(down).abs().compareTo(orig.subtract(up).abs()) < 0) {
            return down;
        }
        return up;
    }
}

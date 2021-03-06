/*
 * Copyright (c) 2015 Vehbi Sinan Tunalioglu <vst@vsthost.com>, Tolga Sezer <tolgasbox@gmail.com>.
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
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.util.MathArrays;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.IntStream;

/**
 * This class provides utilities for matrices implemented as
 * ordinary arrays of double arrays.
 *
 * @author Vehbi Sinan Tunalioglu, Tolga Sezer
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
        return filtered.toArray(new String[filtered.size()]);
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
        return ArrayUtils.toPrimitive(filtered.toArray(new Double[filtered.size()]));
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
     * Get the order of the specified elements in descending or ascending order.
     *
     * @param values A vector of integer values.
     * @param indices The indices which will be considered for ordering.
     * @param descending Flag indicating if we go descending or not.
     * @return A vector of indices sorted in the provided order.
     */
    public static int[] getOrder(int[] values, int[] indices, boolean descending) {
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
     * @param values A vector of integer values.
     * @param descending Flag indicating if we go descending or not.
     * @return A vector of indices sorted in the provided order.
     */
    public static int[] getOrder(int[] values, boolean descending) {
        return DMatrixUtils.getOrder(values, IntStream.range(0, values.length).toArray(), descending);
    }

    /**
     * Get the order of the elements in ascending order.
     *
     * @param values A vector of integer values.
     * @return A vector of indices sorted in the ascending order.
     */
    public static int[] getOrder(int[] values) {
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

        if (Objects.equals(bSteps, BigDecimal.ZERO)) {
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

        if (Objects.equals(bSteps, BigDecimal.ZERO)) {
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

    /**
     * Same functionality as {@link DMatrixUtils#roundToClosest(double, double)} but operating on double values.
     *
     * @param value The value to be rounded.
     * @param steps Steps.
     * @return Rounded value.
     */
    public static double roundDoubleToClosest (double value, double steps) {
        final double down = DMatrixUtils.roundDoubleDownTo(value, steps);
        final double up = DMatrixUtils.roundDoubleUpTo(value, steps);
        if (Math.abs(value - down) < Math.abs(value - up)) {
            return down;
        }
        return up;
    }

    private static double roundDoubleDownTo(double value, double steps) {
        if (steps == 0) {
            return value;
        }
        else {
            return Math.floor(value / steps) * steps;
        }
    }

    private static double roundDoubleUpTo(double value, double steps) {
        if (steps == 0) {
            return value;
        }
        else {
            return Math.ceil(value / steps) * steps;
        }
    }

    /**
     * Creates a matrix from the provided range for columns with the given number of rows.
     *
     * @param lower The lower bound (inclusive) for the range.
     * @param upper The upper bound (exclusive) for the range.
     * @param rows The number of rows.
     * @return A new matrix.
     */
    public static double[][] matrixFromRange (double[] lower, double[] upper, int rows) {
        // Get the number of columns:
        final int cols = lower.length;

        // Initialize the return value:
        final double[][] matrix = new double[rows][cols];

        // Iterate over columns and fill the array:
        for (int col = 0; col < cols; col++) {
            // Get the sequence:
            final double[] sequence = DMatrixUtils.sequence(lower[col], upper[col], rows);

            // Assign to each row in the column:
            for (int row = 0; row < sequence.length; row++) {
                matrix[row][col] = sequence[row];
            }
        }

        // Done, return the populated matrix:
        return matrix;
    }

    /**
     * Computes the cumulative sums of a given vector.
     *
     * @param vector Vector which the cumulative sums to be computed of.
     * @return Cumulative sums of the vector.
     */
    public static double[] cumsum (double[] vector) {
        // Initialize the return value:
        final double[] retval = new double[vector.length];

        // Iterate and cumulate:
        retval[0] = vector[0];
        for (int i = 1; i < retval.length; i++) {
            retval[i] = vector[i] + retval[i - 1];
        }

        // Done, return:
        return retval;
    }

    /**
     * Provides a workhorse function for {@link DMatrixUtils#zmbd} by shuffling indices for operation.
     *
     * @param lower Lower distances.
     * @param upper Upper distances.
     * @param targetMean The target mean (ie. not zero-mean, but target-mean bounded...).
     * @param randomGenerator Random number generator.
     * @return The zero-mean bounded uniform distribution.
     */
    private static double[] _zmbdInner(double[] lower, double[] upper, double targetMean, RandomGenerator randomGenerator) {
        // Define the dimension of the problem:
        final int dimension = lower.length;

        // Define the return value:
        final double[] values = new double[dimension];

        // Get the sum of lowers and uppers:
        final double lowerSum = StatUtils.sum(lower);
        final double upperSum = StatUtils.sum(upper);

        // Initialize the contingencies to cumulative sums:
        final double[] contingencyLower = DMatrixUtils.cumsum(lower);
        final double[] contingencyUpper = DMatrixUtils.cumsum(upper);

        // Iterate over contingencies and update:
        for (int i = 0; i < dimension; i++) {
            contingencyLower[i] = lowerSum - contingencyLower[i];
            contingencyUpper[i] = upperSum - contingencyUpper[i];
        }

        // Define the mean value to be updated:
        double mean = 0.0;

        // Iterate over the dimension in a random order and update permissible draws and mean value:
        double minValue, maxValue;
        for (int index = 0; index < dimension; index++) {
            // Define the min/max value:
            minValue = lower[index];
            maxValue = upper[index];

            // Simulate or get antithetical for the element:
            if (mean == targetMean) {
                // Get correct contingency U/L bounds by sign:
                final double contingencyU = contingencyUpper[index] > 0 ? contingencyUpper[index] : -contingencyUpper[index];
                final double contingencyL = contingencyLower[index] < 0 ? contingencyLower[index] : -contingencyLower[index];

                // Update the effective range:
                minValue = minValue < 0 ? Math.max(minValue, -contingencyU) : Math.min(minValue, -contingencyL);
                maxValue = maxValue > 0 ? Math.min(maxValue, -contingencyL) : Math.max(maxValue, -contingencyU);

                // Get the value:
                if (minValue == maxValue) {
                    values[index] = minValue;

                    if (values[index] < lower[index]) {
                        values[index] = lower[index];
                    }
                    else if(values[index] > upper[index]){
                        values[index] = upper[index];
                    }
                }
                else {
                    values[index] = new UniformRealDistribution(randomGenerator, Math.min(minValue, maxValue), Math.max(minValue, maxValue)).sample();
                }
            }
            else if (mean > 0.0) {
                values[index] = maxValue > -mean ? Math.max(-mean, minValue) : Math.min(-mean, maxValue);
            }
            else {
                values[index] = minValue < -mean ? Math.min(-mean, maxValue) : Math.max(-mean, minValue);
            }

            // Update the mean:
            mean += values[index];
        }

        // Done, return with pride!
        return values;
    }

    /**
     * Computes the zero-mean bounded distribution for the provided distances.
     *
     * <p>
     *
     * Note that if {@code targetMean} is not zero, then the logic is not zero-mean but target-mean bounded.
     *
     * @param lower Lower distances.
     * @param upper Upper distances.
     * @param targetMean The target mean (ie. not zero-mean, but target-mean bounded...).
     * @param randomGenerator Random number generator.
     * @return The zero-mean bounded uniform distribution.
     */
    public static double[] zmbd(double[] lower, double[] upper, double targetMean, RandomGenerator randomGenerator) {
        // Check dimension match:
        if (lower.length != upper.length) {
            throw new IllegalArgumentException("Lower and upper bounds must be of same length.");
        }

        // Check for that lower bounds must be equal to or less than upper bounds.
        for (int i = 0; i < lower.length; i++) {
            if (lower[i] > upper[i]) {
                throw new IllegalArgumentException("Lower bounds must be equal to or less than upper bounds.");
            }
        }

        // Get indices and shuffle:
        final int[] indices = DMatrixUtils.shuffleIndices(lower.length, randomGenerator);

        // Call auxiliary function:
        final double[] zmbdValues = DMatrixUtils._zmbdInner(
                DMatrixUtils.applyIndices(lower, indices),
                DMatrixUtils.applyIndices(upper, indices),
                targetMean,
                randomGenerator);

        // Reapply original indices and return:
        return DMatrixUtils.applyIndices(zmbdValues, DMatrixUtils.getOrder(indices));
    }

    /**
     * Computes the zero-mean bounded distribution for the provided distances.
     *
     * @param lower Lower distances.
     * @param upper Upper distances.
     * @param randomGenerator Random number generator.
     * @return The zero-mean bounded uniform distribution.
     */
    public static double[] zmbd(double[] lower, double[] upper, RandomGenerator randomGenerator) {
        return DMatrixUtils.zmbd(lower, upper, 0.0, randomGenerator);
    }

    /**
     * Returns a target-total bounded distribution sample.
     *
     * @param target The target total value.
     * @param lower Lower limits.
     * @param upper Upper limits.
     * @param randomGenerator The random generator.
     * @return A vector of target-total bounded sample.
     */
    private static double[] _ttbdInner (double target, double[] lower, double[] upper, RandomGenerator randomGenerator) {
        // Initialize the return value:
        final double[] retval = new double[lower.length];

        // Iterate over the retval and simulate:
        for (int i = 0; i < retval.length; i++) {
            if (lower[i] == upper[i]) {
                retval[i] = lower[i];
            }
            else {
                retval[i] = new UniformRealDistribution(randomGenerator, lower[i], upper[i]).sample();
            }
        }

        // Compute the gap of simulated total and target total:
        double gap = target - DMatrixUtils.sum(retval);

        // Iterate over the return values and adjust as per gap:
        for (int i = 0; i < retval.length; i++) {
            // If there is no gap, return the retval:
            if (gap == 0.0) {
                return retval;
            }

            // Calculate the distances to limits:
            final double distanceToLower = lower[i] - retval[i];
            final double distanceToUpper = upper[i] - retval[i];

            // Compute the permissible shift:
            final double shift = gap > 0 ? Math.min(distanceToUpper, gap) : Math.max(distanceToLower, gap);

            // Apply the shift:
            retval[i] += shift;

            // Update gap:
            gap -= shift;
        }

        // Done, return:
        return retval;
    }

    /**
     * Returns a target-total bounded distribution sample.
     *
     * <p>
     *
     * Note that this method is not the actual workhorse. This very method first
     * shuffles indices, calls the workhorse, and readjust as per original indices.
     *
     * @param target The target total value.
     * @param lower Lower limits.
     * @param upper Upper limits.
     * @param randomGenerator The random generator.
     * @return A vector of target-total bounded sample.
     */
    public static double[] ttbd(double target, double[] lower, double[] upper, RandomGenerator randomGenerator) {
        // Check dimension match:
        if (lower.length != upper.length) {
            throw new IllegalArgumentException("Lower and upper bounds must be of same length.");
        }

        // Get indices and shuffle:
        final int[] indices = DMatrixUtils.shuffleIndices(lower.length, randomGenerator);

        // Call auxiliary function:
        final double[] ttbdValues = DMatrixUtils._ttbdInner(
                target,
                DMatrixUtils.applyIndices(lower, indices),
                DMatrixUtils.applyIndices(upper, indices),
                randomGenerator);

        // Reapply original indices and return:
        return DMatrixUtils.applyIndices(ttbdValues, DMatrixUtils.getOrder(indices));
    }

    /**
     * Consumes the length of an array and returns its shuffled indices.
     *
     * @param length The length of the arrays of which indices to be shuffled.
     * @param randomGenerator Random number generator.
     * @return Shuffled indices.
     */
    public static int[] shuffleIndices (int length, RandomGenerator randomGenerator) {
        // Initialize indices:
        final int[] indices = IntStream.range(0, length).toArray();

        // Shuffle the array:
        MathArrays.shuffle(indices, randomGenerator);

        // Done return shuffled indices:
        return indices;
    }

    /**
     * Consumes an array and desired respective indices in an array and return a new array with values from the desired indices.
     *
     * @param vector Values.
     * @param indices Desired indices for order.
     * @return A new array.
     */
    public static double[] applyIndices (double[] vector, int[] indices) {
        // Initialize the return array:
        final double[] retval = new double[indices.length];

        // Iterate over indices and populate:
        for (int i = 0; i < retval.length; i++) {
            retval[i] = vector[indices[i]];
        }

        // Done, return the return value:
        return retval;
    }

    /**
     * Ensures that the vector is limited to {@code limit} (inclusive) as {@code limit} is
     * acting as either lower boundary ({@code min == true}) or upper boundary ({@code min == false}).
     *
     * @param vector The vector to be limited.
     * @param limit The limit.
     * @param min The flag if we should min the limit ({@code true}) or max it ({@code false}).
     * @return Limited vector.
     */
    public static double[] ensureLimit (double[] vector, double limit, boolean min) {
        // Initialize the return value:
        final double[] retval = new double[vector.length];

        // Iterate:
        for (int i = 0; i < retval.length; i++) {
            if (min) {
                if (vector[i] < limit) {
                    retval[i] = limit;
                }
                else {
                    retval[i] = vector[i];
                }
            }
            else {
                if (vector[i] > limit) {
                    retval[i] = limit;
                }
                else {
                    retval[i] = vector[i];
                }
            }
        }

        // Done, return:
        return  retval;
    }

    /**
     * Computes a vector as the min of respective pairs from two arrays.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return Mins of two vectors.
     */
    public static double[] pairwiseMin (double[] vector1, double[] vector2) {
        // Initialize the return value:
        final double[] retval = new double[vector1.length];

        // Iterate over values and get mins:
        for (int i = 0; i < retval.length; i++) {
            retval[i] = Math.min(vector1[i], vector2[i]);
        }

        // Done, return:
        return retval;
    }

    /**
     * Computes a vector as the max of respective pairs from two arrays.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return Maxs of two vectors.
     */
    public static double[] pairwiseMax (double[] vector1, double[] vector2) {
        // Initialize the return value:
        final double[] retval = new double[vector1.length];

        // Iterate over values and get maxs:
        for (int i = 0; i < retval.length; i++) {
            retval[i] = Math.max(vector1[i], vector2[i]);
        }

        // Done, return:
        return retval;
    }
}

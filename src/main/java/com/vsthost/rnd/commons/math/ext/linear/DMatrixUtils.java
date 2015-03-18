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

import java.util.Arrays;

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
}

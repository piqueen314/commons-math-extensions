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

import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.MathArrays;

/**
 * Provides extended functionality for real matrices and vectors from common maths.
 */
public class EMatrixUtils {

    /**
     * Returns the column range from the matrix as a new matrix.
     *
     * @param matrix The input matrix
     * @param start The index of the column to start with (inclusive)
     * @param end The index of the column to end with (inclusive)
     * @return A new matrix with columns specified
     */
    public static RealMatrix getColumRange (RealMatrix matrix, int start, int end) {
        return matrix.getSubMatrix(0, matrix.getRowDimension() - 1, start, end);
    }

    /**
     * Returns the row range from the matrix as a new matrix.
     *
     * @param matrix The input matrix
     * @param start The index of the row to start with (inclusive)
     * @param end The index of the row to end with (inclusive)
     * @return A new matrix with rows specified
     */
    public static RealMatrix getRowRange (RealMatrix matrix, int start, int end) {
        return matrix.getSubMatrix(start, end, 0, matrix.getColumnDimension() - 1);
    }

    /**
     * Returns the sums of columns.
     *
     * @param matrix The matrix of which the sums of columns to be computed
     * @return A double array of column sums
     */
    public static double[] colSums (RealMatrix matrix) {
        // Declare and initialize the return value:
        double[] retval = new double[matrix.getColumnDimension()];

        // Iterate over columns and compute totals:
        for (int col = 0; col < matrix.getColumnDimension(); col++) {
            for (int row = 0; row < matrix.getRowDimension(); row++) {
                retval[col] += matrix.getEntry(row, col);
            }
        }

        // Done, return col sums:
        return retval;
    }

    /**
     * Returns the sums of rows.
     *
     * @param matrix The matrix of which the sums of rows to be computed
     * @return A double array of row sums.
     */
    public static double[] rowSums (RealMatrix matrix) {
        // Declare and initialize the return value:
        double[] retval = new double[matrix.getColumnDimension()];

        // Iterate over columns and compute totals:
        for (int row = 0; row < matrix.getRowDimension(); row++) {
            for (int col = 0; col < matrix.getColumnDimension(); col++) {
                retval[row] += matrix.getEntry(row, col);
            }
        }

        // Done, return col sums:
        return retval;
    }

    /**
     * Returns the means of columns.
     *
     * @param matrix The matrix of which the means of columns to be computed
     * @return A double array of column means
     */
    public static double[] colMeans (RealMatrix matrix) {
        // Get the col sums:
        double[] retval = EMatrixUtils.colSums(matrix);

        // Get the length:
        final int length = matrix.getColumnDimension();

        // Iterate over return value and divide by the length:
        for (int i = 0; i < retval.length; i++) {
            retval[i] = retval[i] / matrix.getRowDimension();
        }

        // Done, return col means:
        return retval;
    }

    /**
     * Returns the means of rows.
     *
     * @param matrix The matrix of which the means of rows to be computed
     * @return A double array of row means
     */
    public static double[] rowMeans (RealMatrix matrix) {
        // Get the col sums:
        double[] retval = EMatrixUtils.rowSums(matrix);

        // Get the length:
        final int length = matrix.getRowDimension();

        // Iterate over return value and divide by the length:
        for (int i = 0; i < retval.length; i++) {
            retval[i] = retval[i] / matrix.getColumnDimension();
        }

        // Done, return row means:
        return retval;
    }

    /**
     * Returns a new matrix by subtracting elements column by column.
     *
     * @param matrix The input matrix
     * @param vector The vector to be subtracted from columns
     * @return A new matrix of which the vector is subtracted column by column
     */
    public static RealMatrix colSubtract (RealMatrix matrix, double[] vector) {
        // Declare and initialize the new matrix:
        double[][] retval = new double[matrix.getRowDimension()][matrix.getColumnDimension()];

        // Iterate over rows:
        for (int col = 0; col < retval.length; col++) {
            // Iterate over columns:
            for (int row = 0; row < retval[0].length; row++) {
                retval[row][col] = matrix.getEntry(row, col) - vector[row];
            }
        }

        // Done, return a new matrix:
        return MatrixUtils.createRealMatrix(retval);
    }

    /**
     * Returns a new matrix by subtracting elements row by row.
     *
     * @param matrix The input matrix
     * @param vector The vector to be subtracted from rows
     * @return A new matrix of which the vector is subtracted row by row
     */
    public static RealMatrix rowSubtract (RealMatrix matrix, double[] vector) {
        // Declare and initialize the new matrix:
        double[][] retval = new double[matrix.getRowDimension()][matrix.getColumnDimension()];

        // Iterate over rows:
        for (int row = 0; row < retval.length; row++) {
            // Iterate over columns:
            for (int col = 0; col < retval[0].length; col++) {
                retval[row][col] = matrix.getEntry(row, col) - vector[col];
            }
        }

        // Done, return a new matrix:
        return MatrixUtils.createRealMatrix(retval);
    }

    /**
     * Returns a new matrix by adding elements row by row.
     *
     * @param matrix The input matrix
     * @param vector The vector to be added to rows
     * @return A new matrix of which the vector is added row by row
     */
    public static RealMatrix columnAdd (RealMatrix matrix, double[] vector) {
        // Declare and initialize the new matrix:
        double[][] retval = new double[matrix.getRowDimension()][matrix.getColumnDimension()];

        // Iterate over rows:
        for (int col = 0; col < retval.length; col++) {
            // Iterate over columns:
            for (int row = 0; row < retval[0].length; row++) {
                retval[row][col] = matrix.getEntry(row, col) + vector[row];
            }
        }

        // Done, return a new matrix:
        return MatrixUtils.createRealMatrix(retval);
    }

    /**
     * Returns a new matrix by adding elements row by row.
     *
     * @param matrix The input matrix
     * @param vector The vector to be added to rows
     * @return A new matrix of which the vector is added row by row
     */
    public static RealMatrix rowAdd (RealMatrix matrix, double[] vector) {
        // Declare and initialize the new matrix:
        double[][] retval = new double[matrix.getRowDimension()][matrix.getColumnDimension()];

        // Iterate over rows:
        for (int row = 0; row < retval.length; row++) {
            // Iterate over columns:
            for (int col = 0; col < retval[0].length; col++) {
                retval[row][col] = matrix.getEntry(row, col) + vector[col];
            }
        }

        // Done, return a new matrix:
        return MatrixUtils.createRealMatrix(retval);
    }

    /**
     * Returns the standard deviations of columns.
     *
     * @param matrix The matrix of which the standard deviations of columns to be computed
     * @return A double array of column standard deviations.
     */
    public static double[] columnStdDevs(RealMatrix matrix) {
        double[] retval = new double[matrix.getColumnDimension()];
        for (int i = 0; i < retval.length; i++) {
            retval[i] = new DescriptiveStatistics(matrix.getColumn(i)).getStandardDeviation();
        }
        return retval;
    }

    /**
     * Returns the standard deviations of rows.
     *
     * @param matrix The matrix of which the standard deviations of rows to be computed
     * @return A double array of row standard deviations.
     */
    public static double[] rowStdDevs(RealMatrix matrix) {
        double[] retval = new double[matrix.getRowDimension()];
        for (int i = 0; i < retval.length; i++) {
            retval[i] = new DescriptiveStatistics(matrix.getRow(i)).getStandardDeviation();
        }
        return retval;
    }

    /**
     * Multiplies the matrix' rows using the vector element-by-element.
     *
     * @param matrix The input matrix.
     * @param vector The vector which will be used to multiply rows of the matrix element-by-element.
     * @return The new matrix of which rows are multiplied with the vector element-by-element.
     */
    public static RealMatrix rbrMultiply(RealMatrix matrix, RealVector vector) {
        // Define the return value:
        RealMatrix retval = MatrixUtils.createRealMatrix(matrix.getRowDimension(), matrix.getColumnDimension());

        // Iterate over rows:
        for (int i = 0; i < retval.getRowDimension(); i++) {
            retval.setRowVector(i, matrix.getRowVector(i).ebeMultiply(vector));
        }

        // Done, return:
        return retval;
    }

    /**
     * Appends to matrices by rows.
     *
     * @param m1 The first matrix
     * @param m2 The second matrix.
     * @return Returns the new row-bound matrix.
     */
    public static RealMatrix rbind (RealMatrix m1, RealMatrix m2) {
        return MatrixUtils.createRealMatrix(ArrayUtils.addAll(m1.getData(), m2.getData()));
    }

    /**
     * Shuffles rows of a matrix.
     *
     * @param matrix The matrix of which the rows will be shuffled.
     * @return The new shuffled matrix.
     */
    public static RealMatrix shuffleRows (RealMatrix matrix) {
        return EMatrixUtils.shuffleRows(matrix, new MersenneTwister());
    }

    /**
     * Shuffles rows of a matrix using the provided random number generator.
     *
     * @param matrix The matrix of which the rows will be shuffled.
     * @param randomGenerator The random number generator to be used.
     * @return The new shuffled matrix.
     */
    public static RealMatrix shuffleRows (RealMatrix matrix, RandomGenerator randomGenerator) {
        // Create an index vector to be shuffled:
        int[] index = MathArrays.sequence(matrix.getRowDimension(), 0, 1);
        MathArrays.shuffle(index, randomGenerator);

        // Create a new matrix:
        RealMatrix retval = MatrixUtils.createRealMatrix(matrix.getRowDimension(), matrix.getColumnDimension());

        // Populate:
        for (int row = 0; row < index.length; row++) {
            retval.setRowVector(row, matrix.getRowVector(index[row]));
        }

        // Done, return:
        return retval;
    }

    /**
     * Converts a real matrix to a JSON string.
     *
     * @return A JSON representation of the matrix.
     */
    public static String toJson (RealMatrix matrix) {
        return new Gson().toJson(matrix.getData());
    }

    /**
     * Converts a real vector to a JSON string.
     *
     * @return A JSON representation of the matrix.
     */
    public static String toJson (RealVector vector) {
        return new Gson().toJson(vector.toArray());
    }
}

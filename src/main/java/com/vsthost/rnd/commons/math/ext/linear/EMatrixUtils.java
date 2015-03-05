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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vst on 26/2/15.
 */
public class EMatrixUtils {

    public static RealMatrix readMatrix (String filepath) throws IOException {
        // Initialize the return value:
        List<double[]> retval = new ArrayList();

        // Initilize the reader:
        Reader in = new FileReader(filepath);

        // Parse and get the iterarable:
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

        // Iterate over the records and populate return value:
        for (CSVRecord record : records) {
            double[] row = new double[record.size()];
            for (int i = 0; i < record.size(); i++) {
                row[i] = Double.parseDouble(record.get(i));
            }
            retval.add(row);
        }

        // Convert the list to an array:
        double[][] retvalArray = new double[retval.size()][];
        retval.toArray(retvalArray);

        // Done, return the array:
        return MatrixUtils.createRealMatrix(retvalArray);
    }

    public static void writeMatrix (double[][] matrix, String filepath) throws IOException {
        Gson gson = new Gson();
        System.out.println(gson.toJson(matrix));
    }

    public static RealMatrix getColumRange (RealMatrix matrix, int start, int end) {
        return matrix.getSubMatrix(0, matrix.getRowDimension() - 1, start, end);
    }

    public static RealMatrix getRowRange (RealMatrix matrix, int start, int end) {
        return matrix.getSubMatrix(start, end, 0, matrix.getColumnDimension() - 1);
    }

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

    public static double means (double[] vector) {
        return EMatrixUtils.sum(vector) / vector.length;
    }

    public static double median (double[] vector) {
        final double[] sorted = vector.clone();
        Arrays.sort(sorted);
        return sorted[vector.length / 2];
    }

    public static double[] colStdDevs (RealMatrix matrix) {
        double[] retval = new double[matrix.getColumnDimension()];
        for (int i = 0; i < retval.length; i++) {
            retval[i] = new DescriptiveStatistics(matrix.getColumn(i)).getStandardDeviation();
        }
        return retval;
    }

    public static double fastExp(double x) {
        x = 1d + x / 256d;
        x *= x; x *= x; x *= x; x *= x;
        x *= x; x *= x; x *= x; x *= x;
        return x;

        // TODO: Find the most efficient exp method.
        //return Math.exp(x);
        //return FastMath.exp(x);
        //return Double.longBitsToDouble(((long) (1512775 * val + 1072632447)) << 32);
        //long tmp = (long) (1512775 * val + (1072693248 - 60801));
        //return Double.longBitsToDouble(tmp << 32);
    }
}

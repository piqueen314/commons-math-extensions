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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides file-based IO utilities for matrices.
 */
public class IOUtils {

    /**
     * Reads a matrix of double values from the filepath provided.
     *
     * @param filepath The path of the file which the matrix will be read from
     * @return A matrix
     * @throws IOException
     */
    public static RealMatrix readMatrix (String filepath) throws IOException {
        // Create a file reader and call the actual implementation:
        return IOUtils.readMatrix(new FileReader(filepath));
    }

    /**
     * Reads a matrix of double values from the reader provided.
     *
     * @param reader The reader which the values to be read from.
     * @return A matrix
     * @throws IOException
     */
    public static RealMatrix readMatrix (Reader reader) throws IOException {
        // Initialize the return value:
        List<double[]> retval = new ArrayList();

        // Parse and get the iterarable:
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

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

}

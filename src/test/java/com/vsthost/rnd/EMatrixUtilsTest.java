package com.vsthost.rnd;

import com.vsthost.rnd.commons.math.ext.linear.EMatrixUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Unit tests for EMatrixUtils.
 */
public class EMatrixUtilsTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EMatrixUtilsTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( EMatrixUtilsTest.class );
    }

    public void testColSums()
    {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(new double[][]{new double[]{1, 2, 3}, new double[]{10, 20, 30}, new double[]{100, 200, 300}});

        // Compute column sums:
        double[] colSums = EMatrixUtils.colSums(testMatrix);

        // Test the length of result array
        assertEquals(colSums.length, 3);

        // Test individual values:
        assertEquals(colSums[0], 111.0);
        assertEquals(colSums[1], 222.0);
        assertEquals(colSums[2], 333.0);

        // Compute column sums for a single element matrix:
        colSums = EMatrixUtils.colSums(MatrixUtils.createRealMatrix(1, 1));

        // Test the length of result array
        assertEquals(colSums.length, 1);

        // Test individual values:
        assertEquals(colSums[0], 0.0);
    }

    public void testRowSums()
    {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(new double[][]{new double[]{1, 2, 3}, new double[]{10, 20, 30}, new double[]{100, 200, 300}});

        // Compute row sums:
        double[] rowSums = EMatrixUtils.rowSums(testMatrix);

        // Test the length of result array
        assertEquals(rowSums.length, 3);

        // Test individual values:
        assertEquals(rowSums[0], 6.0);
        assertEquals(rowSums[1], 60.0);
        assertEquals(rowSums[2], 600.0);

        // Compute row sums for a single element matrix:
        rowSums = EMatrixUtils.rowSums(MatrixUtils.createRealMatrix(1, 1));

        // Test the length of result array
        assertEquals(rowSums.length, 1);

        // Test individual values:
        assertEquals(rowSums[0], 0.0);
    }

    public void testColMeans()
    {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(new double[][]{new double[]{1, 2, 3}, new double[]{10, 20, 30}, new double[]{100, 200, 300}});

        // Compute column sums:
        double[] colMeans = EMatrixUtils.colMeans(testMatrix);

        // Test the length of result array
        assertEquals(colMeans.length, 3);

        // Test individual values:
        assertEquals(colMeans[0], 37.0);
        assertEquals(colMeans[1], 74.0);
        assertEquals(colMeans[2], 111.0);

        // Compute column sums for a single element matrix:
        colMeans = EMatrixUtils.colSums(MatrixUtils.createRealMatrix(1, 1));

        // Test the length of result array
        assertEquals(colMeans.length, 1);

        // Test individual values:
        assertEquals(colMeans[0], 0.0);
    }

    public void testRowMeans()
    {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(new double[][]{new double[]{1, 2, 3}, new double[]{10, 20, 30}, new double[]{100, 200, 300}});

        // Compute row sums:
        double[] rowMeans = EMatrixUtils.rowMeans(testMatrix);

        // Test the length of result array
        assertEquals(rowMeans.length, 3);

        // Test individual values:
        assertEquals(rowMeans[0], 2.0);
        assertEquals(rowMeans[1], 20.0);
        assertEquals(rowMeans[2], 200.0);

        // Compute row sums for a single element matrix:
        rowMeans = EMatrixUtils.rowSums(MatrixUtils.createRealMatrix(1, 1));

        // Test the length of result array
        assertEquals(rowMeans.length, 1);

        // Test individual values:
        assertEquals(rowMeans[0], 0.0);
    }

    public void testSubtractCol()
    {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(new double[][]{new double[]{1, 2, 3}, new double[]{10, 20, 30}, new double[]{100, 200, 300}});

        // Compute row sums:
        RealMatrix subtracted = EMatrixUtils.colSubtract(testMatrix, new double[]{1, 2, 3});

        // Test individual values:
        assertEquals(0.0, subtracted.getEntry(0, 0));
        assertEquals(8.0, subtracted.getEntry(1, 0));
        assertEquals(97.0, subtracted.getEntry(2, 0));
        assertEquals(1.0, subtracted.getEntry(0, 1));
        assertEquals(18.0, subtracted.getEntry(1, 1));
        assertEquals(197.0, subtracted.getEntry(2, 1));
        assertEquals(2.0, subtracted.getEntry(0, 2));
        assertEquals(28.0, subtracted.getEntry(1, 2));
        assertEquals(297.0, subtracted.getEntry(2, 2));
    }

    public void testSubtractRow()
    {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(new double[][]{new double[]{1, 2, 3}, new double[]{10, 20, 30}, new double[]{100, 200, 300}});

        // Compute row sums:
        RealMatrix subtracted = EMatrixUtils.rowSubtract(testMatrix, new double[]{1, 2, 3});

        // Test individual values:
        assertEquals(0.0, subtracted.getEntry(0, 0));
        assertEquals(0.0, subtracted.getEntry(0, 1));
        assertEquals(0.0, subtracted.getEntry(0, 2));
        assertEquals(9.0, subtracted.getEntry(1, 0));
        assertEquals(18.0, subtracted.getEntry(1, 1));
        assertEquals(27.0, subtracted.getEntry(1, 2));
        assertEquals(99.0, subtracted.getEntry(2, 0));
        assertEquals(198.0, subtracted.getEntry(2, 1));
        assertEquals(297.0, subtracted.getEntry(2, 2));
    }

    public void testGetColumnRange () {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(3, 3);
        testMatrix.setRow(0, new double[]{0, 1, 2});
        testMatrix.setRow(1, new double[]{10, 11, 22});
        testMatrix.setRow(2, new double[]{100, 111, 222});

        // Check:
        RealMatrix sliced = EMatrixUtils.getColumRange(testMatrix, 0, 0);
        assertEquals(1, sliced.getColumnDimension());
        assertEquals(3, sliced.getRowDimension());
        assertEquals(0.0, sliced.getEntry(0, 0));
        assertEquals(10.0, sliced.getEntry(1, 0));
        assertEquals(100.0, sliced.getEntry(2, 0));

        // Check:
        sliced = EMatrixUtils.getColumRange(testMatrix, 0, 1);
        assertEquals(2, sliced.getColumnDimension());
        assertEquals(3, sliced.getRowDimension());
        assertEquals(0.0, sliced.getEntry(0, 0));
        assertEquals(10.0, sliced.getEntry(1, 0));
        assertEquals(100.0, sliced.getEntry(2, 0));
        assertEquals(1.0, sliced.getEntry(0, 1));
        assertEquals(11.0, sliced.getEntry(1, 1));
        assertEquals(111.0, sliced.getEntry(2, 1));
    }

    public void testGetRowRange () {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(3, 3);
        testMatrix.setRow(0, new double[]{0, 1, 2});
        testMatrix.setRow(1, new double[]{10, 11, 22});
        testMatrix.setRow(2, new double[]{100, 111, 222});

        // Check:
        RealMatrix sliced = EMatrixUtils.getRowRange(testMatrix, 0, 0);
        assertEquals(3, sliced.getColumnDimension());
        assertEquals(1, sliced.getRowDimension());
        assertEquals(0.0, sliced.getEntry(0, 0));
        assertEquals(1.0, sliced.getEntry(0, 1));
        assertEquals(2.0, sliced.getEntry(0, 2));

        // Check:
        sliced = EMatrixUtils.getRowRange(testMatrix, 0, 1);
        assertEquals(3, sliced.getColumnDimension());
        assertEquals(2, sliced.getRowDimension());
        assertEquals(0.0, sliced.getEntry(0, 0));
        assertEquals(1.0, sliced.getEntry(0, 1));
        assertEquals(2.0, sliced.getEntry(0, 2));
        assertEquals(10.0, sliced.getEntry(1, 0));
        assertEquals(11.0, sliced.getEntry(1, 1));
        assertEquals(22.0, sliced.getEntry(1, 2));
    }

    public void testColumnAdd () {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(3, 3);
        testMatrix.setRow(0, new double[]{0, 1, 2});
        testMatrix.setRow(1, new double[]{10, 11, 22});
        testMatrix.setRow(2, new double[]{100, 111, 222});

        // Check:
        RealMatrix added = EMatrixUtils.columnAdd(testMatrix, new double[]{1, 2, 3});
        assertEquals(3, added.getColumnDimension());
        assertEquals(3, added.getRowDimension());
        assertEquals(1.0, added.getEntry(0, 0));
        assertEquals(2.0, added.getEntry(0, 1));
        assertEquals(3.0, added.getEntry(0, 2));
        assertEquals(12.0, added.getEntry(1, 0));
        assertEquals(13.0, added.getEntry(1, 1));
        assertEquals(24.0, added.getEntry(1, 2));
        assertEquals(103.0, added.getEntry(2, 0));
        assertEquals(114.0, added.getEntry(2, 1));
        assertEquals(225.0, added.getEntry(2, 2));
    }

    public void testRowAdd () {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(3, 3);
        testMatrix.setRow(0, new double[]{0, 1, 2});
        testMatrix.setRow(1, new double[]{10, 11, 22});
        testMatrix.setRow(2, new double[]{100, 111, 222});

        // Check:
        RealMatrix added = EMatrixUtils.rowAdd(testMatrix, new double[]{1, 2, 3});
        assertEquals(3, added.getColumnDimension());
        assertEquals(3, added.getRowDimension());
        assertEquals(1.0, added.getEntry(0, 0));
        assertEquals(3.0, added.getEntry(0, 1));
        assertEquals(5.0, added.getEntry(0, 2));
        assertEquals(11.0, added.getEntry(1, 0));
        assertEquals(13.0, added.getEntry(1, 1));
        assertEquals(25.0, added.getEntry(1, 2));
        assertEquals(101.0, added.getEntry(2, 0));
        assertEquals(113.0, added.getEntry(2, 1));
        assertEquals(225.0, added.getEntry(2, 2));
    }

    public void testRowStd () {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(3, 3);
        testMatrix.setRow(0, new double[]{2, 3, 5});
        testMatrix.setRow(1, new double[]{7, 11, 13});
        testMatrix.setRow(2, new double[]{17, 19, 29});

        // Check:
        double[] stds = EMatrixUtils.rowStdDevs(testMatrix);
        assertEquals(3, stds.length);
        assertEquals(1.52753, stds[0], 0.00001);
        assertEquals(3.05505, stds[1], 0.00001);
        assertEquals(6.42910, stds[2], 0.00001);
    }

    public void testColStd () {
        // Create a sample matrix:
        RealMatrix testMatrix = MatrixUtils.createRealMatrix(3, 3);
        testMatrix.setRow(0, new double[]{2, 3, 5});
        testMatrix.setRow(1, new double[]{7, 11, 13});
        testMatrix.setRow(2, new double[]{17, 19, 29});

        // Check:
        double[] stds = EMatrixUtils.columnStdDevs(testMatrix);
        assertEquals(3, stds.length);
        assertEquals(7.63763, stds[0], 0.00001);
        assertEquals(8.00000, stds[1], 0.00001);
        assertEquals(12.2202, stds[2], 0.00001);
    }

}

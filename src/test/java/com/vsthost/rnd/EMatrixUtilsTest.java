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

}

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

import org.apache.commons.math3.util.FastMath;

/**
 * Provides extensions to standard math functions.
 */
public class ExtMath {

    /**
     * Returns a fast, nearly accurate implementation of exponential function.
     *
     * <p>Note that the implementation is not yet settled. There are faster or more accurate ways to do it.</p>
     *
     * @param x A double value to be provided
     * @return The e^x as a double value.
     */
    public static double fastExp(double x) {
        return FastMath.exp(x);

        // Below, we have other ways of achieving this:
        //
        // Method 1:
        // =========
        // x = 1d + x / 256d;
        // x *= x; x *= x; x *= x; x *= x;
        // x *= x; x *= x; x *= x; x *= x;
        // return x;
        //
        // Method 2:
        // =========
        //
        // return Math.exp(x);
        //
        // Method 3:
        // =========
        //
        // return Double.longBitsToDouble(((long) (1512775 * val + 1072632447)) << 32);
        //
        // Method 4:
        // =========
        //
        // return Double.longBitsToDouble(((long) (1512775 * val + (1072693248 - 60801))) << 32);
    }
}

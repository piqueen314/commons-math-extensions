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

/**
 * Created by vst on 6/3/15.
 */
public class ExtMath {
    public static double fastExp(double x) {
        // TODO: Find the most efficient and accurate exp method.
        x = 1d + x / 256d;
        x *= x; x *= x; x *= x; x *= x;
        x *= x; x *= x; x *= x; x *= x;
        return x;
        //return Math.exp(x);
        //return FastMath.exp(x);
        //return Double.longBitsToDouble(((long) (1512775 * val + 1072632447)) << 32);
        //return Double.longBitsToDouble(((long) (1512775 * val + (1072693248 - 60801))) << 32);
    }
}

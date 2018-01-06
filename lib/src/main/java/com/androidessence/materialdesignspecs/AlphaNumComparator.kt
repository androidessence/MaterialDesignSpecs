package com.androidessence.materialdesignspecs

/*
 * The Alphanum Algorithm is an improved sorting algorithm for strings
 * containing numbers.  Instead of sorting numbers in ASCII order like
 * a standard sort, this algorithm sorts numbers in numeric order.
 *
 * The Alphanum Algorithm is discussed at http://www.DaveKoelle.com
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

import java.util.Comparator

/**
 * This is an updated version with enhancements made by Daniel Migowski,
 * Andre Bogus, and David Koelle
 *
 *
 * To convert to use Templates (Java 1.5+):
 * - Change "implements Comparator" to "implements Comparator<String>"
 * - Change "compare(Object o1, Object o2)" to "compare(String s1, String s2)"
 * - Remove the type checking and casting in compare().
 *
 *
 * To use this class:
 * Use the static "sort" method from the java.util.Collections class:
 * Collections.sort(your list, new AlphanumComparator());
</String> */
internal class AlphaNumComparator : Comparator<String> {
    /**
     * Length of string is passed in for improved efficiency (only need to calculate it once)
     */
    private fun getChunk(s: String, sLength: Int, mauker: Int): String {
        val stringBuilder = StringBuilder()
        var position = mauker

        var currentChar = s[position]
        stringBuilder.append(currentChar)
        position++

        // If we are starting with a digit, let's loop until we hit the end of the digits.
        // Otherwise, loop until we hit a digit.
        if (currentChar.isDigit()) {
            while (position < sLength) {
                currentChar = s[position]
                if (!currentChar.isDigit())
                    break
                stringBuilder.append(currentChar)
                position++
            }
        } else {
            while (position < sLength) {
                currentChar = s[position]
                if (currentChar.isDigit())
                    break
                stringBuilder.append(currentChar)
                position++
            }
        }

        return stringBuilder.toString()
    }

    override fun compare(o1: String?, o2: String?): Int {
        if (o1 == null || o2 == null) {
            return 0
        }

        var mauker1 = 0
        var mauker2 = 0

        val s1Length = o1.length
        val s2Length = o2.length

        while (mauker1 < s1Length && mauker2 < s2Length) {
            val chunk1 = getChunk(o1, s1Length, mauker1)
            mauker1 += chunk1.length

            val chunk2 = getChunk(o2, s2Length, mauker2)
            mauker2 += chunk2.length

            // If both chunks contain numeric characters, sort them numerically
            var result: Int
            if (chunk1[0].isDigit() && chunk2[0].isDigit()) {
                // Simple chunk comparison by length.
                val thisChunkLength = chunk1.length
                result = thisChunkLength - chunk2.length
                // If equal, the first different number counts
                if (result == 0) {
                    for (i in 0 until thisChunkLength) {
                        result = chunk1[i] - chunk2[i]
                        if (result != 0) {
                            return result
                        }
                    }
                }
            } else {
                result = chunk1.compareTo(chunk2)
            }

            if (result != 0) {
                return result
            }
        }

        return s1Length - s2Length
    }
}

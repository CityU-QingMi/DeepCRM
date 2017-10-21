            @Override
            void escape(final StringBuilder toAppendTo, final int start) {
                for (int i = toAppendTo.length() - 1; i >= start; i--) { // backwards: length may change
                    final char c = toAppendTo.charAt(i);
                    if (Character.isISOControl(c)) {
                        // all iso control characters are in U+00xx
                        toAppendTo.setCharAt(i, '\\');
                        toAppendTo.insert(i + 1, "u0000");
                        toAppendTo.setCharAt(i + 4, Chars.getUpperCaseHex((c & 0xF0) >> 4));
                        toAppendTo.setCharAt(i + 5, Chars.getUpperCaseHex(c & 0xF));
                    } else if (c == '"' || c == '\\') {
                        // only " and \ need to be escaped; other escapes are optional
                        toAppendTo.insert(i, '\\');
                    }
                }
            }

    public static int[] computeTable(final String pattern) {

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern must  not be null.");
        } else if (pattern.length() < 2) {
            throw new IllegalArgumentException("Pattern length must be > 1.");
        }

        final int patternLength = pattern.length();

        //
        int[] table = new int[patternLength];
        int   i     = 2;
        int   j     = 0;

        table[0] = -1;
        table[1] = 0;

        while (i < patternLength) {
            if (pattern.charAt(i - 1) == pattern.charAt(j)) {
                table[i] = j + 1;

                j++;
                i++;
            } else if (j > 0) {
                j = table[j];
            } else {
                table[i] = 0;

                i++;

                j = 0;
            }
        }

        return table;
    }

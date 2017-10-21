    public static int[] computeTable(final char[] pattern) {

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern must  not be null.");
        } else if (pattern.length < 2) {
            throw new IllegalArgumentException("Pattern length must be > 1.");
        }

        int[] table = new int[pattern.length];
        int   i     = 2;
        int   j     = 0;

        table[0] = -1;
        table[1] = 0;

        while (i < pattern.length) {
            if (pattern[i - 1] == pattern[j]) {
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

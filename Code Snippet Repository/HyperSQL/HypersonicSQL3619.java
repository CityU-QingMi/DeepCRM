    public static boolean startsWith(String value, int[][] ranges) {

        int ch = value.charAt(0);

        for (int i = 0; i < ranges.length; i++) {
            if (ch > ranges[i][1]) {
                continue;
            }

            if (ch < ranges[i][0]) {
                return false;
            }

            return true;
        }

        return false;
    }

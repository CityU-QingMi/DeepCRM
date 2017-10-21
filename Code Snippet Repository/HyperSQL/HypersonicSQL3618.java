    public static boolean isInSet(String value, int[][] ranges) {

        int length = value.length();

        mainLoop:
        for (int index = 0; index < length; index++) {
            int ch = value.charAt(index);

            for (int i = 0; i < ranges.length; i++) {
                if (ch > ranges[i][1]) {
                    continue;
                }

                if (ch < ranges[i][0]) {
                    return false;
                }

                continue mainLoop;
            }

            return false;
        }

        return true;
    }

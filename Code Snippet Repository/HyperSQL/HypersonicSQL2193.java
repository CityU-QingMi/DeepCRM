    public static int rightTrimSize(String s) {

        int i = s.length();

        while (i > 0) {
            i--;

            if (s.charAt(i) != ' ') {
                return i + 1;
            }
        }

        return 0;
    }

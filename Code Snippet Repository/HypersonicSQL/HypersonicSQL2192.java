    public static boolean isEmpty(String s) {

        int i = s == null ? 0
                          : s.length();

        while (i > 0) {
            if (s.charAt(--i) > ' ') {
                return false;
            }
        }

        return true;
    }

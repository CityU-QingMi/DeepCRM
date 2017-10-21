    public static int skipSpaces(String s, int start) {

        int limit = s.length();
        int i     = start;

        for (; i < limit; i++) {
            if (s.charAt(i) != ' ') {
                break;
            }
        }

        return i;
    }

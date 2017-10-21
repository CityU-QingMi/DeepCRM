    public static int getRightTrimSize(String s, char trim) {

        int endindex = s.length();

        for (--endindex; endindex >= 0 && s.charAt(endindex) == trim;
                endindex--) {}

        endindex++;

        return endindex;
    }

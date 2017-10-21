    public static String intArrayToString(int[] intArray) {
        StringBuilder bldr = new StringBuilder();
        for (int s : intArray) {
            if (bldr.length() > 0) {
                bldr.append(",").append(Integer.toString(s));
            } else {
                bldr.append(Integer.toString(s));
            }
        }
        return bldr.toString();
    }

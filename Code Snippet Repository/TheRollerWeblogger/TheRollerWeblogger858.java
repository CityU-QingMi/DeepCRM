    public static int[] stringToIntArray(String instr, String delim) {
        String[] str = StringUtils.split(instr, delim);
        int intArray[] = new int[str.length];
        int i = 0;
        for (String string : str) {
            int nInt = Integer.parseInt(string);
            intArray[i++] = nInt;
        }
        return intArray;
    }

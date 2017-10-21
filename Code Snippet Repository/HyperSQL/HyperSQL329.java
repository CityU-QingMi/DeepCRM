    static int pastName(String inString, int startIndex) {
        String workString = inString.substring(startIndex);
        int    e          = inString.length();  // Index 1 past end of var name.
        int    nonVarIndex;

        for (char nonVarChar : nonVarChars) {
            nonVarIndex = workString.indexOf(nonVarChar);

            if (nonVarIndex > -1 && nonVarIndex < e) e = nonVarIndex;
        }

        return startIndex + e;
    }

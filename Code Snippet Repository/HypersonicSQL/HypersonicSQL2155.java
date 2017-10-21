    public static String expandSysPropVars(String inString) {

        String outString = inString;
        int    varOffset, varEnd;
        String varVal, varName;

        while (true) {

            // Recursive substitution for ${x} variables.
            varOffset = outString.indexOf("${");

            if (varOffset < 0) {
                break;
            }

            varEnd = outString.indexOf('}', varOffset + 2);

            if (varEnd < 0) {
                break;
            }

            varName = outString.substring(varOffset + 2, varEnd);

            if (varName.length() < 1) {
                throw new IllegalArgumentException("Bad variable setting");
            }

            varVal = System.getProperty(varName);

            if (varVal == null) {
                throw new IllegalArgumentException(
                    "No Java system property with name '" + varName + "'");
            }

            outString = outString.substring(0, varOffset) + varVal
                        + outString.substring(varEnd + 1);
        }

        return outString;
    }

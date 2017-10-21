    public static String substVars(final String val, final Properties props) throws
        IllegalArgumentException {

        final StringBuilder sbuf = new StringBuilder();

        int i = 0;
        int j;
        int k;

        while (true) {
            j = val.indexOf(DELIM_START, i);
            if (j == -1) {
                // no more variables
                if (i == 0) { // this is a simple string
                    return val;
                }
                // add the tail string which contails no variables and return the result.
                sbuf.append(val.substring(i, val.length()));
                return sbuf.toString();
            }
            sbuf.append(val.substring(i, j));
            k = val.indexOf(DELIM_STOP, j);
            if (k == -1) {
                throw new IllegalArgumentException(Strings.dquote(val)
                    + " has no closing brace. Opening brace at position " + j
                    + '.');
            }
            j += DELIM_START_LEN;
            final String key = val.substring(j, k);
            // first try in System properties
            String replacement = PropertiesUtil.getProperties().getStringProperty(key, null);
            // then try props parameter
            if (replacement == null && props != null) {
                replacement = props.getProperty(key);
            }

            if (replacement != null) {
                // Do variable substitution on the replacement string
                // such that we can solve "Hello ${x2}" as "Hello p1"
                // the where the properties are
                // x1=p1
                // x2=${x1}
                final String recursiveReplacement = substVars(replacement, props);
                sbuf.append(recursiveReplacement);
            }
            i = k + DELIM_STOP_LEN;
        }
    }

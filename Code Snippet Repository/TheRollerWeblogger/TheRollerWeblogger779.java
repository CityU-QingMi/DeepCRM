    private static String truncateNicely(String str, int lower, int upper, String appendToEnd)
    {
        // strip markup from the string
        str = removeXml(str);

        // quickly adjust the upper if it is set lower than 'lower'
        if(upper < lower) {
            upper = lower;
        }

        // now determine if the string fits within the upper limit
        // if it does, go straight to return, do not pass 'go' and collect $200
        if(str.length() > upper) {
            // the magic location int
            int loc;

            // first we determine where the next space appears after lower
            loc = str.lastIndexOf(' ', upper);

            // now we'll see if the location is greater than the lower limit
            if(loc >= lower) {
                // yes it was, so we'll cut it off here
                str = str.substring(0, loc);
            } else {
                // no it wasn't, so we'll cut it off at the upper limit
                str = str.substring(0, upper);
            }

            // the string was truncated, so we append the appendToEnd String
            str = str + appendToEnd;
        }

        return str;
    }

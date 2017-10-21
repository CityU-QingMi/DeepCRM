    public static int getMaximumCacheValidity(final String pattern) {
        //
        //   If there are more "S" in the pattern than just one "SSS" then
        //      (for example, "HH:mm:ss,SSS SSS"), then set the expiration to
        //      one millisecond which should only perform duplicate request caching.
        //
        final int firstS = pattern.indexOf('S');

        if ((firstS >= 0) && (firstS != pattern.lastIndexOf("SSS"))) {
            return 1;
        }

        return DEFAULT_VALIDITY;
    }

    public boolean acceptsURL(String url) {

        if (url == null) {
            return false;
        }

        if (url.regionMatches(true, 0, DatabaseURL.S_URL_PREFIX, 0,
                              DatabaseURL.S_URL_PREFIX.length())) {
            return true;
        }

        if (url.regionMatches(true, 0, DatabaseURL.S_URL_INTERNAL, 0,
                              DatabaseURL.S_URL_INTERNAL.length())) {
            return true;
        }

        return false;
    }

    public static boolean shouldRetry(Exception ex) {
        // Determine if error appears transient (warranting retrial)
        // We give most errors the "benefit of the doubt" by considering them transient
        // This picks out a few that we consider non-transient
        if (ex instanceof UnknownHostException) {
            // User probably mistyped the url in the custom target.
            return false;
        } else if (ex instanceof MalformedURLException) {
            // This should never happen due to validations but if we get here, retrial won't fix it.
            return false;
        }
        return true;
    }

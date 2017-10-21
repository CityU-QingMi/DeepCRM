    public static Date parseIfModifiedSince(String headerValue) {
        for (FastDateFormat fastDateFormat : IF_MODIFIED_SINCE_FORMATS) {
            try {
                return fastDateFormat.parse(headerValue);
            } catch (ParseException ignore) {
                LOG.debug("Error parsing value [{}] as [{}]!", headerValue, fastDateFormat);
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Error parsing value [{}] as date!", headerValue);
        }
        return null;
    }

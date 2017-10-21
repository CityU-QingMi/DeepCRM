    protected Pattern getUrlPattern() {
        if (StringUtils.isNotEmpty(urlRegexExpression)) {
            String regex = (String) parse(urlRegexExpression, String.class);
            if (regex == null) {
                LOG.warn("Provided URL Regex expression [{}] was evaluated to null! Falling back to default!", urlRegexExpression);
                urlPattern = Pattern.compile(DEFAULT_URL_REGEX, Pattern.CASE_INSENSITIVE);
            } else {
                urlPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            }
        }
        return urlPattern;
    }

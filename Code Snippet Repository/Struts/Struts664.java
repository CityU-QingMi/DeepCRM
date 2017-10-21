    protected void logConfigurationException(HttpServletRequest request, ConfigurationException e) {
        // WW-2874 Only log error if in devMode
        String uri = request.getRequestURI();
        if (request.getQueryString() != null) {
            uri = uri + "?" + request.getQueryString();
        }
        if (devMode) {
            LOG.error("Could not find action or result: {}", uri, e);
        } else if (LOG.isWarnEnabled()) {
            LOG.warn("Could not find action or result: {}", uri, e);
        }
    }

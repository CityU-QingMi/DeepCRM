    protected Resource findResource(Map<String, ResultTypeConfig> resultsByExtension, String... parts) {
        for (String ext : resultsByExtension.keySet()) {
            String canonicalPath = canonicalize(string(parts) + "." + ext);
            LOG.trace("Checking for {}", canonicalPath);

            try {
                if (servletContext.getResource(canonicalPath) != null) {
                    return new Resource(canonicalPath, ext);
                }
            } catch (MalformedURLException e) {
                LOG.error("Unable to parse path to the web application resource {} skipping...", canonicalPath);
            }
        }

        return null;
    }

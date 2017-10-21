    protected Result findResult(String path, String resultCode, String ext, ActionContext actionContext,
                                Map<String, ResultTypeConfig> resultsByExtension) {
        try {
            LOG.trace("Checking ServletContext for {}", path);

            URL resource = servletContext.getResource(path);
            if (resource != null && resource.getPath().endsWith(path)) {
                LOG.trace("Found resource {}", resource);
                return buildResult(path, resultCode, resultsByExtension.get(ext), actionContext);
            }

            LOG.trace("Checking ClassLoader for {}", path);

            String classLoaderPath = path.startsWith("/") ? path.substring(1, path.length()) : path;
            resource = ClassLoaderUtil.getResource(classLoaderPath, getClass());
            if (resource != null && resource.getPath().endsWith(classLoaderPath)) {
                LOG.trace("Found resource {}", resource);
                return buildResult(path, resultCode, resultsByExtension.get(ext), actionContext);
            }
        } catch (MalformedURLException e) {
            LOG.error("Unable to parse template path: {} skipping...", path);
        }

        return null;
    }

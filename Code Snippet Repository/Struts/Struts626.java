    private InputStream readPropertyUsingServletContext(String propName) {
        ServletContext servletContext = ServletActionContext.getServletContext();
        String path = propName.startsWith("/") ? propName : "/" + propName;
        if (servletContext != null) {
            return servletContext.getResourceAsStream(path);
        } else {
            LOG.warn("ServletContext is null, cannot obtain {}", path);
            return null;
        }
    }

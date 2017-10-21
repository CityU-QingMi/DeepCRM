    public synchronized Configuration getConfiguration(ServletContext servletContext) {
        if (config == null) {
            try {
                init(servletContext);
            } catch (TemplateException e) {
                LOG.error("Cannot load freemarker configuration: ", e);
            }
            // store this configuration in the servlet context
            servletContext.setAttribute(CONFIG_SERVLET_CONTEXT_KEY, config);
        }
        return config;
    }

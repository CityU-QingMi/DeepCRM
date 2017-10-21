    protected Configuration createConfiguration(ServletContext servletContext) throws TemplateException {
        Version incompatibleImprovements = getFreemarkerVersion(servletContext);

        Configuration configuration = new Configuration(incompatibleImprovements);

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        if (mruMaxStrongSize > 0) {
            LOG.debug("Sets Configuration.CACHE_STORAGE_KEY to strong:{}", mruMaxStrongSize);
            configuration.setSetting(Configuration.CACHE_STORAGE_KEY, "strong:" + mruMaxStrongSize);
        }
        if (templateUpdateDelay != null) {
            LOG.debug("Sets Configuration.TEMPLATE_UPDATE_DELAY_KEY to {}", templateUpdateDelay);
            configuration.setSetting(Configuration.TEMPLATE_UPDATE_DELAY_KEY, templateUpdateDelay);
        }
        if (encoding != null) {
            LOG.debug("Sets DefaultEncoding to {}", encoding);
            configuration.setDefaultEncoding(encoding);
        }
        LOG.debug("Disabled localized lookups");
        configuration.setLocalizedLookup(false);
        LOG.debug("Enabled whitespace stripping");
        configuration.setWhitespaceStripping(true);
        LOG.debug("Sets NewBuiltinClassResolver to TemplateClassResolver.SAFER_RESOLVER");
        configuration.setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);

        return configuration;
    }

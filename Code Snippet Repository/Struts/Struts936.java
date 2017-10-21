    public void init(ServletContext servletContext) throws TemplateException {
        config = createConfiguration(servletContext);

        // Set defaults:
        config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        contentType = DEFAULT_CONTENT_TYPE;

        // Process object_wrapper init-param out of order:
        wrapper = createObjectWrapper(servletContext);
        LOG.debug("Using object wrapper of class {}", wrapper.getClass().getName());
        config.setObjectWrapper(wrapper);

        // Process TemplatePath init-param out of order:
        templatePath = servletContext.getInitParameter(INITPARAM_TEMPLATE_PATH);
        if (templatePath == null) {
            templatePath = servletContext.getInitParameter("templatePath");
        }

        configureTemplateLoader(createTemplateLoader(servletContext, templatePath));

        loadSettings(servletContext);
    }

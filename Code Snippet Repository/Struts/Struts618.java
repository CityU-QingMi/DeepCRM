    protected void mergeTemplate(Writer writer, Template template) throws Exception {
        final TemplateEngine engine = templateEngineManager.getTemplateEngine(template, templateSuffix);
        if (engine == null) {
            throw new ConfigurationException("Unable to find a TemplateEngine for template " + template);
        }

        LOG.debug("Rendering template {}", template);

        final TemplateRenderingContext context = new TemplateRenderingContext(template, writer, getStack(), getParameters(), this);
        engine.renderTemplate(context);
    }

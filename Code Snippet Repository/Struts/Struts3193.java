    public void renderTemplate(TemplateRenderingContext templateContext) throws Exception {
        Template t = templateContext.getTemplate();
        Theme theme = themes.get(t.getTheme());
        if (theme == null) {
            // Theme not supported, so do what struts would have done if we were not here.
            LOG.debug("Theme not found [{}] trying default template engine using template type [{}]", t.getTheme(), defaultTemplateType);
            final TemplateEngine engine = templateEngineManager.getTemplateEngine(templateContext.getTemplate(), defaultTemplateType);

            if (engine == null) {
                // May be the default template has changed?
                throw new ConfigurationException("Unable to find a TemplateEngine for template type '" + defaultTemplateType
                        + "' whilst trying to render template " + templateContext.getTemplate());
            } else {
                try {
                    // Retry render
                    engine.renderTemplate(templateContext);
                } catch (Exception e) {
                    // Give up and throw a new StrutsException(e);
                    throw new StrutsException("Cannot render tag [" + t.getName() + "] because theme ["
                            + t.getTheme() + "] was not found.", e);
                }
            }
        } else {
            // Render our template
            theme.renderTag(t.getName(), templateContext);
        }
    }

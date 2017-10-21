    public GSPRenderer(TemplateEngine templateEngine, WeblogTemplate template) {
        this.template = template;
        try {
            // TODO: implement caching for compiled templates
            groovyTemplate = templateEngine.createTemplate(template.getContents());
        } catch (Exception ex) {
            log.debug("Creating Groovy template", ex);
            parseException = ex;
        }
    }

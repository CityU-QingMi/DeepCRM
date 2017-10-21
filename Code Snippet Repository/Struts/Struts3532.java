    private void executeRenderResult(String location, ActionInvocation invocation)
            throws TemplateException, IOException, PortletException {
        this.location = location;
        this.invocation = invocation;
        this.configuration = getConfiguration();
        this.wrapper = getObjectWrapper();

        HttpServletRequest req = ServletActionContext.getRequest();

        if (!location.startsWith("/")) {
            String base = ResourceUtil.getResourceBase(req);
            location = base + "/" + location;
        }

        Template template = configuration.getTemplate(location, deduceLocale());
        TemplateModel model = createModel();
        // Give subclasses a chance to hook into preprocessing
        if (preTemplateProcess(template, model)) {
            try {
                // Process the template
                PortletActionContext.getRenderResponse().setContentType(pContentType);
                template.process(model, getWriter());
            } finally {
                // Give subclasses a chance to hook into postprocessing
                postTemplateProcess(template, model);
            }
        }
    }

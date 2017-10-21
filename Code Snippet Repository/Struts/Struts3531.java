    public void doExecute(String location, ActionInvocation invocation)
            throws IOException, TemplateException, PortletException {
        PortletPhase phase = PortletActionContext.getPhase();
        if (phase.isAction()) {
           executeActionResult(location, invocation);
        } else if (phase.isRender()) {
           executeRenderResult(location, invocation);
        } else if (phase.isResource()){
           executeResourceResult(location, invocation);
        }
    }

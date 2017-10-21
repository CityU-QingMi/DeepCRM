    protected void executeActionResult(String location, ActionInvocation invocation) {
        ActionResponse res = PortletActionContext.getActionResponse();

        res.setRenderParameter(PortletConstants.ACTION_PARAM, TILES_ACTION_NAME);

        Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
        sessionMap.put(PortletConstants.RENDER_DIRECT_LOCATION, location);

        res.setRenderParameter(PortletConstants.MODE_PARAM, PortletActionContext.getRequest().getPortletMode().toString());
    }

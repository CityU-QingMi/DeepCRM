    protected void executeRenderResult(String location) throws TilesException {
        setLocation(location);

        PortletContext portletContext = PortletActionContext.getPortletContext();
        RenderRequest request = PortletActionContext.getRenderRequest();
        RenderResponse response = PortletActionContext.getRenderResponse();

        TilesContainer container = getCurrentContainer(request, portletContext);
        ApplicationContext applicationContext = container.getApplicationContext();
        Request currentRequest = new RenderPortletRequest(applicationContext, portletContext, request, response);

        container.render(location, currentRequest);
    }

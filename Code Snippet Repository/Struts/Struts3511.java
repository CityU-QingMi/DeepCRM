    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response)
            throws PortletException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Entering serveResource");
        }
        resetActionContext();
        try {
            serviceAction(request, response,
                    getRequestMap(request), getParameterMap(request),
                    getSessionMap(request), getApplicationMap(),
                    portletNamespace, PortletPhase.SERVE_RESOURCE_PHASE);
        } finally {
            ActionContext.setContext(null);
        }
    }

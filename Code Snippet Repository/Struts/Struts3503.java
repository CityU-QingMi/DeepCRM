    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Entering processAction in mode ", request.getPortletMode().toString());
        }
        resetActionContext();
        try {
            serviceAction(request, response, getRequestMap(request), getParameterMap(request),
                    getSessionMap(request), getApplicationMap(),
                    portletNamespace, PortletPhase.ACTION_PHASE);
            if (LOG.isDebugEnabled()) LOG.debug("Leaving processAction");
        } finally {
            ActionContext.setContext(null);
        }
    }

    @Override
    public void processEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Entering processEvent");
        }
        resetActionContext();
        try {
            // We'll use the event name as the "action"
            serviceAction(request, response,
                    getRequestMap(request), getParameterMap(request),
                    getSessionMap(request), getApplicationMap(),
                    portletNamespace, PortletPhase.EVENT_PHASE);
            if (LOG.isDebugEnabled()) LOG.debug("Leaving processEvent");
        } finally {
            ActionContext.setContext(null);
        }
    }

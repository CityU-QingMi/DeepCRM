    public void render(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {

        if (LOG.isDebugEnabled()) {
            LOG.debug("Entering render in mode ", request.getPortletMode().toString());
        }
        resetActionContext();
        response.setTitle(getTitle(request));
        if (!request.getWindowState().equals(WindowState.MINIMIZED)) {
            try {
                // Check to see if an event set the render to be included directly
                serviceAction(request, response, getRequestMap(request), getParameterMap(request),
                        getSessionMap(request), getApplicationMap(),
                        portletNamespace, PortletPhase.RENDER_PHASE);
                if (LOG.isDebugEnabled()) LOG.debug("Leaving render");
            } finally {
                resetActionContext();
            }
        }
    }

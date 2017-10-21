    protected void initPortletContext(ActionContext actionContext) {
        LOG.debug("Initializing mock portlet environment");
        portletContext = new MockContext();
        portletContext.setMajorVersion(getMajorVersion());
        actionContext.put(StrutsStatics.STRUTS_PORTLET_CONTEXT, portletContext);

        portletRequest = new MockPortletRequest(portletContext);
        portletResponse = new MockStateAwareResponse();
        portletSession = new MockPortletSession();
        portletRequest.setSession(portletSession);
        actionContext.setSession(createSession());
        actionContext.put(PortletConstants.REQUEST, portletRequest);
        actionContext.put(PortletConstants.RESPONSE, portletResponse);
        actionContext.put(PortletConstants.MODE_NAMESPACE_MAP, new HashMap<PortletMode, String>());
        actionContext.put(PortletConstants.PHASE, PortletPhase.EVENT_PHASE);
    }

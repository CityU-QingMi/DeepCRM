    public void setUp() throws Exception {
        super.setUp();

        ActionProxy actionProxy = getActionProxy("/portlettest/test"); // creates new empty ActionContext
        ActionContext.getContext().put(ActionContext.ACTION_INVOCATION, actionProxy.getInvocation());

        PortletContext portletCtx = new MockPortletContext();
        ActionContext.getContext().put(StrutsStatics.STRUTS_PORTLET_CONTEXT, portletCtx);
        ActionContext.getContext().put(PortletConstants.REQUEST, new MockPortletRequest(portletCtx));
        ActionContext.getContext().put(PortletConstants.RESPONSE, new MockMimeResponse());
        ActionContext.getContext().put(PortletConstants.MODE_NAMESPACE_MAP, Collections.emptyMap());

        stack = actionProxy.getInvocation().getStack();
    }

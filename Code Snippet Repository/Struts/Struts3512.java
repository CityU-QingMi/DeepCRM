    public String intercept(ActionInvocation invocation) throws Exception {
        final Object action = invocation.getAction();
        final ActionContext context = invocation.getInvocationContext();

        if (action instanceof PortletRequestAware) {
            PortletRequest request = (PortletRequest) context.get(PortletConstants.REQUEST);
            ((PortletRequestAware) action).setPortletRequest(request);
        }

        if (action instanceof PortletResponseAware) {
            PortletResponse response = (PortletResponse) context.get(PortletConstants.RESPONSE);
            ((PortletResponseAware) action).setPortletResponse(response);
        }
        if (action instanceof PrincipalAware) {
            PortletRequest request = (PortletRequest) context.get(PortletConstants.REQUEST);
            ((PrincipalAware) action).setPrincipalProxy(new PortletPrincipalProxy(request));
        }
        if (action instanceof PortletContextAware) {
            PortletContext portletContext = (PortletContext) context.get(StrutsStatics.STRUTS_PORTLET_CONTEXT);
            ((PortletContextAware) action).setPortletContext(portletContext);
        }
        if (action instanceof PortletPreferencesAware) {
        	PortletRequest request = (PortletRequest) context.get(PortletConstants.REQUEST);
            
            // Check if running in a servlet environment
            if (request == null) {
                LOG.warn("This portlet preferences implementation should only be used during development");
                ((PortletPreferencesAware)action).setPortletPreferences(new ServletPortletPreferences(ActionContext.getContext().getSession()));
            } else {
            	((PortletPreferencesAware)action).setPortletPreferences(request.getPreferences());
            }
        }
        return invocation.invoke();
    }

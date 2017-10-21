    public String intercept(ActionInvocation invocation) throws Exception {
        final Object action = invocation.getAction();
        final ActionContext context = invocation.getInvocationContext();

        if (action instanceof ServletRequestAware) {
            HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
            ((ServletRequestAware) action).setServletRequest(request);
        }

        if (action instanceof ServletResponseAware) {
            HttpServletResponse response = (HttpServletResponse) context.get(HTTP_RESPONSE);
            ((ServletResponseAware) action).setServletResponse(response);
        }

        if (action instanceof ParameterAware) {
            context.getParameters().applyParameters((ParameterAware) action);
        }

        if (action instanceof HttpParametersAware) {
            ((HttpParametersAware) action).setParameters(context.getParameters());
        }

        if (action instanceof ApplicationAware) {
            ((ApplicationAware) action).setApplication(context.getApplication());
        }
        
        if (action instanceof SessionAware) {
            ((SessionAware) action).setSession(context.getSession());
        }
        
        if (action instanceof RequestAware) {
            ((RequestAware) action).setRequest((Map) context.get("request"));
        }

        if (action instanceof PrincipalAware) {
            HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
            if(request != null) {
                // We are in servtlet environment, so principal information resides in HttpServletRequest
                ((PrincipalAware) action).setPrincipalProxy(new ServletPrincipalProxy(request));
            }
        }
        if (action instanceof ServletContextAware) {
            ServletContext servletContext = (ServletContext) context.get(SERVLET_CONTEXT);
            ((ServletContextAware) action).setServletContext(servletContext);
        }
        return invocation.invoke();
    }

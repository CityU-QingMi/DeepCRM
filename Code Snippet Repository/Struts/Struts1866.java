    protected void setUp() throws Exception {
        loadConfigurationProviders(new WaitConfigurationProvider());

        session = new HashMap();
        params = new HashMap();
        context = new HashMap();
        context.put(ActionContext.SESSION, session);
        context.put(ActionContext.PARAMETERS, HttpParameters.create().build());

        request = new StrutsMockHttpServletRequest();
        httpSession = new StrutsMockHttpSession();
        request.setSession(httpSession);
        request.setParameterMap(params);
        context.put(ServletActionContext.HTTP_REQUEST, request);
        container.inject(parametersInterceptor);
    }

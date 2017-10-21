    protected void setUp() throws Exception {
        loadConfigurationProviders(new TestConfigurationProvider());

        session = new TreeMap<>();
        params = new TreeMap<>();
        extraContext = new TreeMap<>();
        extraContext.put(ActionContext.SESSION, session);
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create().build());

        request = new StrutsMockHttpServletRequest();
        httpSession = new StrutsMockHttpSession();
        request.setSession(httpSession);
        request.setParameterMap(params);
        extraContext.put(ServletActionContext.HTTP_REQUEST, request);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.getContext().putAll(extraContext);
        oldContext = new ActionContext(stack.getContext());
        ActionContext.setContext(oldContext);
    }

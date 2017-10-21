    public void setUp() throws Exception {
        interceptor = new I18nInterceptor();
        interceptor.setLocaleProviderFactory(new DefaultLocaleProviderFactory());
        interceptor.init();
        session = new HashMap();

        Map<String, Object> ctx = new HashMap<String, Object>();
        ctx.put(ActionContext.PARAMETERS, HttpParameters.create().build());
        ctx.put(ActionContext.SESSION, session);

        ac = new ActionContext(ctx);

        ServletActionContext.setContext(ac);
        ServletActionContext.setRequest(new MockHttpServletRequest());

        Action action = new Action() {
            public String execute() throws Exception {
                return SUCCESS;
            }
        };

        MockActionProxy proxy = new MockActionProxy();
        proxy.setAction(action);
        proxy.setNamespace("i18n");
        proxy.setActionName("anAction");

        mai = new MockActionInvocation();
        ((MockActionInvocation) mai).setAction(action);
        ((MockActionInvocation) mai).setInvocationContext(ac);
        ((MockActionInvocation) mai).setProxy(proxy);
    }

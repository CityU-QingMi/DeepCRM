    public ValidationAwareSupport doPost(String namespace, String actionName, Map params) throws Exception {
        HttpServletRequest req = WebContextFactory.get().getHttpServletRequest();
        ServletContext servletContext = WebContextFactory.get().getServletContext();
        HttpServletResponse res = WebContextFactory.get().getHttpServletResponse();

        HttpParameters.Builder requestParams = HttpParameters.create(req.getParameterMap());
        if (params != null) {
            requestParams = requestParams.withExtraParams(params);
        }
        Map requestMap = new RequestMap(req);
        Map session = new SessionMap(req);
        Map application = new ApplicationMap(servletContext);
        Dispatcher du = Dispatcher.getInstance();
        HashMap<String, Object> ctx = du.createContextMap(requestMap,
                requestParams.build(),
                session,
                application,
                req,
                res);

        try {
            ActionProxyFactory actionProxyFactory = du.getContainer().getInstance(ActionProxyFactory.class);
            ActionProxy proxy = actionProxyFactory.createActionProxy(namespace, actionName, null, ctx, true, true);
            proxy.execute();
            Object action = proxy.getAction();

            if (action instanceof ValidationAware) {
                ValidationAware aware = (ValidationAware) action;
                ValidationAwareSupport vas = new ValidationAwareSupport();
                vas.setActionErrors(aware.getActionErrors());
                vas.setActionMessages(aware.getActionMessages());
                vas.setFieldErrors(aware.getFieldErrors());

                return vas;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOG.error("Error while trying to validate", e);
            return null;
        }
    }

    protected void createMocks() throws Exception {
        action = this.getAction();
        container.inject(action);

        stack = ActionContext.getContext().getValueStack();
        context = stack.getContext();
        stack.push(action);

        request = new StrutsMockHttpServletRequest();
        request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, stack);
        response = new StrutsMockHttpServletResponse();
        request.setSession(new StrutsMockHttpSession());
        request.setupGetServletPath("/");

        writer = new StringWriter();

        JspWriter jspWriter = new StrutsMockJspWriter(writer);

        servletContext.setRealPath(new File("nosuchfile.properties").getAbsolutePath());
        servletContext.setServletInfo("Resin");

        pageContext = new StrutsMockPageContext();
        pageContext.setRequest(request);
        pageContext.setResponse(response);
        pageContext.setJspWriter(jspWriter);
        pageContext.setServletContext(servletContext);

        mockContainer = new Mock(Container.class);
        MockDispatcher du = new MockDispatcher(pageContext.getServletContext(), new HashMap<String, String>(), configurationManager);
        du.init();
        Dispatcher.setInstance(du);
        session = new SessionMap(request);
        Map<String, Object> extraContext = du.createContextMap(new RequestMap(request),
                HttpParameters.create(request.getParameterMap()).build(),
                session,
                new ApplicationMap(pageContext.getServletContext()),
                request,
                response);
        // let's not set the locale -- there is a test that checks if Dispatcher actually picks this up...
        // ... but generally we want to just use no locale (let it stay system default)
        extraContext.remove(ActionContext.LOCALE);
        stack.getContext().putAll(extraContext);

        context.put(ServletActionContext.HTTP_REQUEST, request);
        context.put(ServletActionContext.HTTP_RESPONSE, response);
        context.put(ServletActionContext.SERVLET_CONTEXT, servletContext);

        ActionContext.setContext(new ActionContext(context));
    }

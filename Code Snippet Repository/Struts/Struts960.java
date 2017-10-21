    public static ValueStack getStack(PageContext pageContext) {
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        ValueStack stack = (ValueStack) req.getAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY);

        if (stack == null) {

            HttpServletResponse res = (HttpServletResponse) pageContext.getResponse();
            Dispatcher du = Dispatcher.getInstance();
            if (du == null) {
                throw new ConfigurationException("The Struts dispatcher cannot be found.  This is usually caused by "+
                        "using Struts tags without the associated filter. Struts tags are only usable when the request "+
                        "has passed through its servlet filter, which initializes the Struts dispatcher needed for this tag.");
            }
            stack = du.getContainer().getInstance(ValueStackFactory.class).createValueStack();

            HttpParameters params = HttpParameters.create(req.getParameterMap()).build();

            Map<String, Object> extraContext = du.createContextMap(new RequestMap(req),
                    params,
                    new SessionMap(req),
                    new ApplicationMap(pageContext.getServletContext()),
                    req,
                    res);
            extraContext.put(ServletActionContext.PAGE_CONTEXT, pageContext);
            stack.getContext().putAll(extraContext);
            req.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, stack);

            // also tie this stack/context to the ThreadLocal
            ActionContext.setContext(new ActionContext(stack.getContext()));
        } else {
            // let's make sure that the current page context is in the action context
            Map<String, Object> context = stack.getContext();
            context.put(ServletActionContext.PAGE_CONTEXT, pageContext);

            AttributeMap attrMap = new AttributeMap(context);
            context.put("attr", attrMap);
        }

        return stack;
    }

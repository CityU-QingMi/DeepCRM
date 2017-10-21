    protected Map createExtraContext() {
        HttpParameters newParams = createParametersForContext();

        ActionContext ctx = new ActionContext(stack.getContext());
        PageContext pageContext = (PageContext) ctx.get(ServletActionContext.PAGE_CONTEXT);
        Map session = ctx.getSession();
        Map application = ctx.getApplication();

        Dispatcher du = Dispatcher.getInstance();
        Map<String, Object> extraContext = du.createContextMap(new RequestMap(req),
                newParams,
                session,
                application,
                req,
                res);

        ValueStack newStack = valueStackFactory.createValueStack(stack);
        extraContext.put(ActionContext.VALUE_STACK, newStack);

        // add page context, such that ServletDispatcherResult will do an include
        extraContext.put(ServletActionContext.PAGE_CONTEXT, pageContext);

        return extraContext;
    }

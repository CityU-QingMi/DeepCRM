    public static Dispatcher initDispatcher(ServletContext ctx, Map<String,String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Dispatcher du = new DispatcherWrapper(ctx, params);
        du.init();
        Dispatcher.setInstance(du);

        // Reset the value stack
        Container container = du.getContainer();
        ValueStack stack = container.getInstance(ValueStackFactory.class).createValueStack();
        stack.getContext().put(ActionContext.CONTAINER, container);
        ActionContext.setContext(new ActionContext(stack.getContext()));
        
        return du;
    }

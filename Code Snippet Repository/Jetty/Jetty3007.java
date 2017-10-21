    protected void expandHandler(Handler handler, List<Handler> list, Class<?> byClass)
    {
        if (handler==null)
            return;

        if (byClass==null || byClass.isAssignableFrom(handler.getClass()))
            list.add(handler);

        if (handler instanceof AbstractHandlerContainer)
            ((AbstractHandlerContainer)handler).expandChildren(list, byClass);
        else if (handler instanceof HandlerContainer)
        {
            HandlerContainer container = (HandlerContainer)handler;
            Handler[] handlers=byClass==null?container.getChildHandlers():container.getChildHandlersByClass(byClass);
            list.addAll(Arrays.asList(handlers));
        }
    }

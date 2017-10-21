    private void checkIfContextIsFree(String path)
    {
        Handler serverHandler = _server.getHandler();
        if (serverHandler instanceof ContextHandler)
        {
            ContextHandler ctx = (ContextHandler) serverHandler;
            if (ctx.getContextPath().equals(path))
                throw new RuntimeException("another context already bound to path " + path);
        }

        Handler[] handlers = _server.getHandlers();
        if (handlers == null) return;

        for (Handler handler : handlers)
        {
            if (handler instanceof ContextHandler) {
                ContextHandler ctx = (ContextHandler) handler;
                if (ctx.getContextPath().equals(path))
                    throw new RuntimeException("another context already bound to path " + path);
            }
        }
    }

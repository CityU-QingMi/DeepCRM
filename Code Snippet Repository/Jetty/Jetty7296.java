    @Override
    protected void startServer(Handler handler) throws Exception
    {
        if (handler == context)
        {
            // Add this listener before the context is started, so it's durable.
            context.addEventListener(new ContextHandler.ContextScopeListener()
            {
                @Override
                public void enterScope(Context context, Request request, Object reason)
                {
                    checkScope();
                    scope.set(new RuntimeException());
                }

                @Override
                public void exitScope(Context context, Request request)
                {
                    assertScope();
                    scope.set(null);
                }
            });
        }
        super.startServer(handler);
    }

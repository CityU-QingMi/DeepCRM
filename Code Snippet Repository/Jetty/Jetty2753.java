    @Override
    public void start(final Runnable task)
    {
        final HttpChannel channel = state().getHttpChannel();
        channel.execute(new Runnable()
        {
            @Override
            public void run()
            {
                state().getAsyncContextEvent().getContext().getContextHandler().handle(channel.getRequest(),task);
            }
        });
    }

    @Override
    public Request onRequestQueued(final QueuedListener listener)
    {
        return requestListener(new QueuedListener()
        {
            @Override
            public void onQueued(Request request)
            {
                listener.onQueued(request);
            }
        });
    }

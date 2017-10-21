    @Override
    public Request onRequestBegin(final BeginListener listener)
    {
        return requestListener(new BeginListener()
        {
            @Override
            public void onBegin(Request request)
            {
                listener.onBegin(request);
            }
        });
    }

    @Override
    public Request onRequestHeaders(final HeadersListener listener)
    {
        return requestListener(new HeadersListener()
        {
            @Override
            public void onHeaders(Request request)
            {
                listener.onHeaders(request);
            }
        });
    }

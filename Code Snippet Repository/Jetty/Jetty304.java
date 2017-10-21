    @Override
    public Request onResponseHeaders(final Response.HeadersListener listener)
    {
        this.responseListeners.add(new Response.HeadersListener()
        {
            @Override
            public void onHeaders(Response response)
            {
                listener.onHeaders(response);
            }
        });
        return this;
    }

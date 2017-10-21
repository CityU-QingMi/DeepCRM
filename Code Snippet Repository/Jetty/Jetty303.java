    @Override
    public Request onResponseHeader(final Response.HeaderListener listener)
    {
        this.responseListeners.add(new Response.HeaderListener()
        {
            @Override
            public boolean onHeader(Response response, HttpField field)
            {
                return listener.onHeader(response, field);
            }
        });
        return this;
    }

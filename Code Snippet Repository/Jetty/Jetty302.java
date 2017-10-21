    @Override
    public Request onResponseBegin(final Response.BeginListener listener)
    {
        this.responseListeners.add(new Response.BeginListener()
        {
            @Override
            public void onBegin(Response response)
            {
                listener.onBegin(response);
            }
        });
        return this;
    }

    @Override
    public Request onResponseContentAsync(final Response.AsyncContentListener listener)
    {
        this.responseListeners.add(new Response.AsyncContentListener()
        {
            @Override
            public void onContent(Response response, ByteBuffer content, Callback callback)
            {
                listener.onContent(response, content, callback);
            }
        });
        return this;
    }

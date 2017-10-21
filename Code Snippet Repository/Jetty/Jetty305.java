    @Override
    public Request onResponseContent(final Response.ContentListener listener)
    {
        this.responseListeners.add(new Response.AsyncContentListener()
        {
            @Override
            public void onContent(Response response, ByteBuffer content, Callback callback)
            {
                try
                {
                    listener.onContent(response, content);
                    callback.succeeded();
                }
                catch (Throwable x)
                {
                    callback.failed(x);
                }
            }
        });
        return this;
    }

    @Override
    public Request onComplete(final Response.CompleteListener listener)
    {
        this.responseListeners.add(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                listener.onComplete(result);
            }
        });
        return this;
    }

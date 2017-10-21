    @Override
    public Request onResponseFailure(final Response.FailureListener listener)
    {
        this.responseListeners.add(new Response.FailureListener()
        {
            @Override
            public void onFailure(Response response, Throwable failure)
            {
                listener.onFailure(response, failure);
            }
        });
        return this;
    }

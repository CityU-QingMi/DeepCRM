    @Override
    public Request onResponseSuccess(final Response.SuccessListener listener)
    {
        this.responseListeners.add(new Response.SuccessListener()
        {
            @Override
            public void onSuccess(Response response)
            {
                listener.onSuccess(response);
            }
        });
        return this;
    }

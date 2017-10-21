    @Override
    public Request onRequestSuccess(final SuccessListener listener)
    {
        return requestListener(new SuccessListener()
        {
            @Override
            public void onSuccess(Request request)
            {
                listener.onSuccess(request);
            }
        });
    }

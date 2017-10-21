    @Override
    public Request onRequestFailure(final FailureListener listener)
    {
        return requestListener(new FailureListener()
        {
            @Override
            public void onFailure(Request request, Throwable failure)
            {
                listener.onFailure(request, failure);
            }
        });
    }

    @Override
    public Request onRequestCommit(final CommitListener listener)
    {
        return requestListener(new CommitListener()
        {
            @Override
            public void onCommit(Request request)
            {
                listener.onCommit(request);
            }
        });
    }

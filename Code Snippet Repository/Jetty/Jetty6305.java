    @OnError
    public void onError(Throwable t)
    {
        if (t == null)
        {
            addError(new NullPointerException("Throwable should not be null"));
        }
        else
        {
            addError(t);
        }
    }

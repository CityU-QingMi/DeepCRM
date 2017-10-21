    private void notifyFailure(Throwable failure)
    {
        WriteCallback callback;
        synchronized (this)
        {
            callback = this.callback;
        }
        if (callback != null)
        {
            callback.writeFailed(failure);
        }
    }

    private void notifySuccess()
    {
        WriteCallback callback;
        synchronized (this)
        {
            callback = this.callback;
        }
        if (callback != null)
        {
            callback.writeSuccess();
        }
    }

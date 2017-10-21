    @OnWebSocketClose
    public void onClose(int code, String reason)
    {
        session = null;
        remoteLock.lock();
        try
        {
            remote = null;
        }
        finally
        {
            remoteLock.unlock();
        }
    }

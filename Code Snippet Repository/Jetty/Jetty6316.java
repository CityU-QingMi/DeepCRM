    public void sendMessage(String msg) throws IOException
    {
        remoteLock.lock();
        try
        {
            RemoteEndpoint r = remote;
            if (r == null)
            {
                return;
            }

            r.sendStringByFuture(msg);
            if (r.getBatchMode() == BatchMode.ON)
                r.flush();
        }
        finally
        {
            remoteLock.unlock();
        }
    }

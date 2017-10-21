    public synchronized void setStopAtShutdown(boolean stop)
    {
        if (stop)
        {
            if (!stopAtShutdown && isStarted() && !ShutdownThread.isRegistered(this))
                ShutdownThread.register(this);
        }
        else
            ShutdownThread.deregister(this);

        stopAtShutdown = stop;
    }

    @Override
    public void onOpen()
    {
        super.onOpen();
        if (listeners != null && !listeners.isEmpty())
        {
            for (NetworkTrafficListener listener : listeners)
            {
                try
                {
                    listener.opened(getSocket());
                }
                catch (Exception x)
                {
                    LOG.warn(x);
                }
            }
        }
    }

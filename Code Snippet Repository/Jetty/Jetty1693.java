    @Override
    public void onClose()
    {
        super.onClose();
        if (listeners != null && !listeners.isEmpty())
        {
            for (NetworkTrafficListener listener : listeners)
            {
                try
                {
                    listener.closed(getSocket());
                }
                catch (Exception x)
                {
                    LOG.warn(x);
                }
            }
        }
    }

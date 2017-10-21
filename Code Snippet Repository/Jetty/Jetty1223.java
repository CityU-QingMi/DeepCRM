    private HttpChannelOverHTTP2 pollHttpChannel()
    {
        if (isRecycleHttpChannels())
        {
            synchronized (this)
            {
                return channels.poll();
            }
        }
        else
        {
            return null;
        }
    }

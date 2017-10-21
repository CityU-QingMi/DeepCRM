    private void offerHttpChannel(HttpChannelOverHTTP2 channel)
    {
        if (isRecycleHttpChannels())
        {
            synchronized (this)
            {
                channels.offer(channel);
            }
        }
    }

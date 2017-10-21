    @Override
    public void onWebSocketText(String message)
    {
        LOG.debug("onWebSocketText({})",message);
        messageQueue.offer(message);
        dataLatch.countDown();

        if (messageExchanger != null)
        {
            try
            {
                messageExchanger.exchange(message);
            }
            catch (InterruptedException e)
            {
                LOG.debug(e);
            }
        }
    }

    private int getMaxMessageSize(int defaultMaxMessageSize, OnMessageCallable... onMessages)
    {
        for (OnMessageCallable callable : onMessages)
        {
            if (callable == null)
            {
                continue;
            }
            OnMessage onMsg = callable.getMethod().getAnnotation(OnMessage.class);
            if (onMsg == null)
            {
                continue;
            }
            if (onMsg.maxMessageSize() > 0)
            {
                return (int)onMsg.maxMessageSize();
            }
        }
        return defaultMaxMessageSize;
    }

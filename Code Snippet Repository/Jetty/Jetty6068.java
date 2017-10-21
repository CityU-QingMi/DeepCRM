    private void updateMessageHandlerSet()
    {
        messageHandlerSet.clear();
        for (MessageHandlerWrapper wrapper : wrappers)
        {
            if (wrapper == null)
            {
                // skip empty
                continue;
            }
            messageHandlerSet.add(wrapper.getHandler());
        }
    }

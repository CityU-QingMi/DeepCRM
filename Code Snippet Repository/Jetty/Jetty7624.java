    private void sendMessageToAllMembers(String message, String username, Map<String, Member> room)
    {
        LOG.debug("Sending message: {} from: {}", message, username);
        for (Member m : room.values())
        {
            synchronized (m)
            {
                m._queue.add(username); // from
                m._queue.add(message);  // chat

                // wakeup member if polling
                AsyncContext async = m._async.get();
                LOG.debug("Async found: {}", async);
                if (async != null & m._async.compareAndSet(async, null))
                {
                    LOG.debug("dispatch");
                    async.dispatch();
                }
            }
        }
    }

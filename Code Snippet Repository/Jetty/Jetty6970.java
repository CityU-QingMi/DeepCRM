    private void notifySessionListeners(Consumer<WebSocketSession.Listener> consumer)
    {
        for (WebSocketSession.Listener listener : listeners)
        {
            try
            {
                consumer.accept(listener);
            }
            catch (Throwable x)
            {
                LOG.info("Exception while invoking listener " + listener, x);
            }
        }
    }

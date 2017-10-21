    private Map<Integer, Integer> notifyPreface(Session session)
    {
        try
        {
            return listener.onPreface(session);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
            return null;
        }
    }

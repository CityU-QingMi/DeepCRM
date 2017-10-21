    @Override
    public final void incomingError(Throwable e)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("incomingError(" + e.getClass().getName() + ")",e);
        }

        onError(e);
    }

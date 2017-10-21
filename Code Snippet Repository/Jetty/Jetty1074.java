    @Override
    public boolean updateClose(boolean update, boolean local)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Update close for {} close={} local={}", this, update, local);

        if (!update)
            return false;

        while (true)
        {
            CloseState current = closeState.get();
            switch (current)
            {
                case NOT_CLOSED:
                {
                    CloseState newValue = local ? CloseState.LOCALLY_CLOSED : CloseState.REMOTELY_CLOSED;
                    if (closeState.compareAndSet(current, newValue))
                        return false;
                    break;
                }
                case LOCALLY_CLOSED:
                {
                    if (local)
                        return false;
                    close();
                    return true;
                }
                case REMOTELY_CLOSED:
                {
                    if (!local)
                        return false;
                    close();
                    return true;
                }
                default:
                {
                    return false;
                }
            }
        }
    }

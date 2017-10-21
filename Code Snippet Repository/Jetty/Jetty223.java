    protected boolean idle(Connection connection, boolean close)
    {
        if (close)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Connection idle close {}", connection);
            return false;
        }
        else
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Connection idle {}", connection);
            return true;
        }
    }

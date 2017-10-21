    @Override
    public boolean remove(Connection connection)
    {
        Holder holder;
        lock();
        try
        {
            holder = quarantine.remove(connection);
        }
        finally
        {
            unlock();
        }

        if (holder == null)
            return super.remove(connection);

        if (LOG.isDebugEnabled())
            LOG.debug("Removed while validating {}", connection);

        boolean cancelled = holder.cancel();
        if (cancelled)
            return remove(connection, true);

        return super.remove(connection);
    }

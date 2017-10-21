    @Override
    public boolean release(Connection connection)
    {
        lock();
        try
        {
            if (!getActiveConnections().remove(connection))
                return false;
            Holder holder = new Holder(connection);
            holder.task = scheduler.schedule(holder, timeout, TimeUnit.MILLISECONDS);
            quarantine.put(connection, holder);
            if (LOG.isDebugEnabled())
                LOG.debug("Validating for {}ms {}", timeout, connection);
        }
        finally
        {
            unlock();
        }

        released(connection);
        return true;
    }

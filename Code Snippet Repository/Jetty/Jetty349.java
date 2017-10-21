    protected boolean remove(Connection connection, boolean force)
    {
        boolean activeRemoved = true;
        boolean idleRemoved = false;
        lock();
        try
        {
            Holder holder = muxedConnections.remove(connection);
            if (holder == null)
                holder = busyConnections.remove(connection);
            if (holder == null)
            {
                activeRemoved = false;
                for (Iterator<Holder> iterator = idleConnections.iterator(); iterator.hasNext();)
                {
                    holder = iterator.next();
                    if (holder.connection == connection)
                    {
                        idleRemoved = true;
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        finally
        {
            unlock();
        }

        if (activeRemoved || force)
            released(connection);
        boolean removed = activeRemoved || idleRemoved || force;
        if (removed)
            removed(connection);
        return removed;
    }

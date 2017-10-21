    @Override
    protected Connection activate()
    {
        Holder holder;
        lock();
        try
        {
            while (true)
            {
                if (muxedConnections.isEmpty())
                {
                    holder = idleConnections.poll();
                    if (holder == null)
                        return null;
                    muxedConnections.put(holder.connection, holder);
                }
                else
                {
                    holder = muxedConnections.values().iterator().next();
                }

                if (holder.count < maxMultiplex)
                {
                    ++holder.count;
                    break;
                }
                else
                {
                    muxedConnections.remove(holder.connection);
                    busyConnections.put(holder.connection, holder);
                }
            }
        }
        finally
        {
            unlock();
        }

        return active(holder.connection);
    }

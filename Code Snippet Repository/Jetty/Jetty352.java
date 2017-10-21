    @Override
    public boolean sweep()
    {
        List<Connection> toSweep = new ArrayList<>();
        lock();
        try
        {
            busyConnections.values().stream()
                    .map(holder -> holder.connection)
                    .filter(connection -> connection instanceof Sweeper.Sweepable)
                    .collect(Collectors.toCollection(() -> toSweep));
            muxedConnections.values().stream()
                    .map(holder -> holder.connection)
                    .filter(connection -> connection instanceof Sweeper.Sweepable)
                    .collect(Collectors.toCollection(() -> toSweep));
        }
        finally
        {
            unlock();
        }

        for (Connection connection : toSweep)
        {
            if (((Sweeper.Sweepable)connection).sweep())
            {
                boolean removed = remove(connection, true);
                LOG.warn("Connection swept: {}{}{} from active connections{}{}",
                        connection,
                        System.lineSeparator(),
                        removed ? "Removed" : "Not removed",
                        System.lineSeparator(),
                        dump());
            }
        }

        return false;
    }

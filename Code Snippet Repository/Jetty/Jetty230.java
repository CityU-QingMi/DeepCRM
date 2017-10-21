    @Override
    public boolean sweep()
    {
        List<Connection> toSweep;
        lock();
        try
        {
            toSweep = activeConnections.stream()
                    .filter(connection -> connection instanceof Sweeper.Sweepable)
                    .collect(Collectors.toList());
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

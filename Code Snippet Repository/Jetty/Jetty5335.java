    public void check() throws IllegalStateException
    {
        int required = allocations.stream()
            .mapToInt(Lease::getThreads)
            .sum();

        int maximum = pool.getMaxThreads();

        if (required>=maximum)
        {
            infoOnLeases();
            throw new IllegalStateException(String.format("Insuffient configured threads: required=%d < max=%d for %s", required, maximum, pool));
        }

        if ((maximum-required) < warnAt)
        {
            infoOnLeases();
            if (warned.compareAndSet(false,true))
                LOG.warn("Low configured threads: ( max={} - required={} ) < warnAt={} for {}", maximum, required, warnAt, pool);
        }
    }

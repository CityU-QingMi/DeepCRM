    public ALPNServerConnectionFactory(@Name("protocols") String... protocols)
    {
        super("alpn", protocols);

        IllegalStateException failure = new IllegalStateException("No Server ALPNProcessors!");
        // Use a for loop on iterator so load exceptions can be caught and ignored
        for (Iterator<Server> i = ServiceLoader.load(Server.class).iterator(); i.hasNext();)
        {
            Server processor;
            try
            {
                processor = i.next();
            }
            catch(Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug(x);
                failure.addSuppressed(x);
                continue;
            }

            try
            {
                processor.init();
                processors.add(processor);
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Could not initialize " + processor, x);
                failure.addSuppressed(x);
            }
        }
        
        if (LOG.isDebugEnabled())
        {
            LOG.debug("protocols: {}", Arrays.asList(protocols));
            LOG.debug("processors: {}", processors);
        }

        if (processors.isEmpty())
            throw failure;
    }

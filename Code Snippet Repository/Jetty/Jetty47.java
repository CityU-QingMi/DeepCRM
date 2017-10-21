    public ALPNClientConnectionFactory(Executor executor, ClientConnectionFactory connectionFactory, List<String> protocols)
    {
        super(connectionFactory);
        if (protocols.isEmpty())
            throw new IllegalArgumentException("ALPN protocol list cannot be empty");
        this.executor = executor;
        this.protocols = protocols;

        IllegalStateException failure = new IllegalStateException("No Client ALPNProcessors!");

        // Use a for loop on iterator so load exceptions can be caught and ignored
        for (Iterator<Client> i = ServiceLoader.load(Client.class).iterator(); i.hasNext();)
        {
            Client processor;
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
            LOG.debug("protocols: {}", protocols);
            LOG.debug("processors: {}", processors);
        }

        if (processors.isEmpty())
            throw failure;
    }

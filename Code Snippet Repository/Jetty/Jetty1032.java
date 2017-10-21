    @Override
    public void succeeded()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Written {} frames for {}", actives.size(), actives);

        complete();

        super.succeeded();
    }

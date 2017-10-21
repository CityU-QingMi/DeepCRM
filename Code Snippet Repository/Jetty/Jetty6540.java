    public void addImplementation(EventDriverImpl impl)
    {
        if (implementations.contains(impl))
        {
            LOG.warn("Ignoring attempt to add duplicate EventDriverImpl: " + impl);
            return;
        }

        implementations.add(impl);
    }

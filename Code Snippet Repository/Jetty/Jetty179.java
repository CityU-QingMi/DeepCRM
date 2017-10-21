    public void destroy()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("destroy() - {} beans",beans.size());
        }
        for (List<ScopedInstance<?>> instances : beans.values())
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("destroying - {} instance(s)",instances.size());
            }
            for (ScopedInstance<?> instance : instances)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("destroying instance {}",instance);
                }
                instance.destroy();
            }
        }
    }

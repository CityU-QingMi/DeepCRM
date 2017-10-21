    public void addBean(ScopedInstance<?> instance)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("addBean({})",instance);
        }
        List<ScopedInstance<?>> instances = getBeans(instance.bean);
        if (instances == null)
        {
            instances = new ArrayList<>();
            beans.put(instance.bean,instances);
        }
        instances.add(instance);
    }

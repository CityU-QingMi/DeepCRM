    public boolean offer(Sweepable sweepable)
    {
        List<Sweepable> refs = items.get();
        if (refs == null)
            return false;
        refs.add(sweepable);
        if (LOG.isDebugEnabled())
            LOG.debug("Resource offered {}", sweepable);
        return true;
    }

    @Override
    public void run()
    {
        List<Sweepable> refs = items.get();
        if (refs == null)
            return;
        for (Sweepable sweepable : refs)
        {
            try
            {
                if (sweepable.sweep())
                {
                    refs.remove(sweepable);
                    if (LOG.isDebugEnabled())
                        LOG.debug("Resource swept {}", sweepable);
                }
            }
            catch (Throwable x)
            {
                LOG.info("Exception while sweeping " + sweepable, x);
            }
        }
        activate();
    }

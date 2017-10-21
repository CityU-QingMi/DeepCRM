    @Override
    public void run()
    {
        try
        {
            while (isRunning())
            {
                @SuppressWarnings("unchecked")
                LeakInfo leakInfo = (LeakInfo)queue.remove();
                if (LOG.isDebugEnabled())
                    LOG.debug("Resource GC'ed: {}",leakInfo);
                if (resources.remove(leakInfo.id) != null)
                    leaked(leakInfo);
            }
        }
        catch (InterruptedException x)
        {
            // Exit
        }
    }

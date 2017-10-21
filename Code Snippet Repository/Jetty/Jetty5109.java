    @ManagedOperation("")
    public void dumpStdErr()
    {
        try
        {
            dump(System.err, "");
        }
        catch (IOException e)
        {
            LOG.warn(e);
        }
    }

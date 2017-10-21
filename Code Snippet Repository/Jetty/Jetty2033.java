    @ManagedOperation(value="", impact="")
    public String getDeadlockStacktraces()
    {
        try
        {
            final ThreadInfo[] threads = findDeadlock();
            if (threads == null)
            {
                // no deadlock, we're done
                return null;
            }

            return stacktraces(threads,0);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

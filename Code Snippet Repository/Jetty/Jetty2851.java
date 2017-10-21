    public boolean failed(Throwable x)
    {
        synchronized (_inputQ)
        {
            // Errors may be reported multiple times, for example
            // a local idle timeout and a remote I/O failure.
            if (isError())
            {
                if (LOG.isDebugEnabled())
                {
                    // Log both the original and current failure
                    // without modifying the original failure.
                    Throwable failure = new Throwable(_state.getError());
                    failure.addSuppressed(x);
                    LOG.debug(failure);
                }
            }
            else
            {
                // Add a suppressed throwable to capture this stack
                // trace without wrapping/hiding the original failure.
                x.addSuppressed(new Throwable("HttpInput failure"));
                _state = new ErrorState(x);
            }
            return wakeup();
        }
    }

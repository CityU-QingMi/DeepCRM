    @ManagedOperation("")
    public void reset()
    {
        _startMillis.set(System.currentTimeMillis());
        _messagesIn.reset();
        _messagesOut.reset();
        _connectionStats.reset();
        _connectionDurationStats.reset();
        _samples.clear();
    }

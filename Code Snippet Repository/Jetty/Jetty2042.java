    public MonitorAction(EventTrigger trigger, EventNotifier notifier, long pollInterval, long pollDelay)
        throws InvalidParameterException
    {
        if (trigger == null)
            throw new InvalidParameterException("Trigger cannot be null");
        
        _id = randomUUID().toString();
        _trigger = trigger;
        _notifier = notifier;
        _pollInterval = pollInterval > 0 ? pollInterval : DEFAULT_POLL_INTERVAL;
        _pollDelay = pollDelay > 0 ? pollDelay : _pollInterval;
    }

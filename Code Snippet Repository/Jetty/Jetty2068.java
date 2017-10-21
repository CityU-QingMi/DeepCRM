    public boolean match(long timestamp)
        throws Exception
    {
        for(EventTrigger trigger : _triggers)
        {
            if (trigger.match(timestamp))
                return true;
        }
        return false;
    }

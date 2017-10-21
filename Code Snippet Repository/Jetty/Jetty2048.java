    @Override
    public boolean match(long timestamp) throws Exception
    {
        boolean result = false;
        for(EventTrigger trigger : _triggers)
        {
            result = trigger.match(timestamp) ? true : result;
        }
        return true;
    }

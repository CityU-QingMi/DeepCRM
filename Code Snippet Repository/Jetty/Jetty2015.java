    private synchronized boolean add(MonitorAction... actions)
    {
        boolean result = true;

        for (MonitorAction action : actions)
        {
            if (!_actions.add(action))
            {
                result = false;
            }
            else
            {
                MonitorTask.schedule(action);
            }
        }
        
        return result;
    }

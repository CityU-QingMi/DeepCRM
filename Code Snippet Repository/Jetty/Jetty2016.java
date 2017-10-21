    private synchronized boolean remove(MonitorAction... actions)
    {
        boolean result = true;

        for (MonitorAction action : actions)
        {
            if (!_actions.remove(action))
            {
                result = false;
            }

            MonitorTask.cancel(action);
        }
        
        return result;
    }

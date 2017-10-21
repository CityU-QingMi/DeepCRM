    public boolean acquired(T resource)
    {
        String id = id(resource);
        LeakInfo info = resources.putIfAbsent(id, new LeakInfo(resource,id));
        if (info != null)
        {
            // Leak detected, prior acquire exists (not released) or id clash.
            return false;
        }
        // Normal behavior.
        return true;
    }

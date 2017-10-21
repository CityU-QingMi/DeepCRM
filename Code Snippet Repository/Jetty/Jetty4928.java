    public boolean released(T resource)
    {
        String id = id(resource);
        LeakInfo info = resources.remove(id);
        if (info != null)
        {
            // Normal behavior.
            return true;
        }

        // Leak detected (released without acquire).
        return false;
    }

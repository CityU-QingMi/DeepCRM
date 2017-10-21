    public boolean equals (Object o)
    {
        if (o==null)
            return false;
        if (!(o instanceof LifeCycleCallback))
            return false;
        LifeCycleCallback callback = (LifeCycleCallback)o;

        if (callback.getTargetClass()==null)
        {
            if (getTargetClass() != null)
                return false;
        }
        else if(!callback.getTargetClass().equals(getTargetClass()))
           return false;
        if (callback.getTarget()==null)
        {
            if (getTarget() != null)
                return false;
        }
        else if (!callback.getTarget().equals(getTarget()))
            return false;

        return true;
    }

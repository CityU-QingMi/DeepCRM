    @Override
    public boolean addBean(Object o)
    {
        if (o instanceof LifeCycle)
        {
            LifeCycle l = (LifeCycle)o;
            return addBean(o,l.isRunning()?Managed.UNMANAGED:Managed.AUTO);
        }

        return addBean(o,Managed.POJO);
    }

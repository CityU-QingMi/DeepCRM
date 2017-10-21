    public void callPostConstructCallback (Object o)
    throws Exception
    {
        if (o == null)
            return;
        
        Class<? extends Object> clazz = o.getClass();
        List<LifeCycleCallback> callbacks = postConstructCallbacksMap.get(clazz.getName());

        if (callbacks == null)
            return;

        for (int i=0;i<callbacks.size();i++)
        {
            ((LifeCycleCallback)callbacks.get(i)).callback(o);
        }
    }

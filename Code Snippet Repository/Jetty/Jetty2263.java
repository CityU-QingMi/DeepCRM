    public void add (LifeCycleCallback callback)
    {
        if ((callback==null) || (callback.getTargetClassName()==null))
            return;

        if (LOG.isDebugEnabled())
            LOG.debug("Adding callback for class="+callback.getTargetClass()+ " on "+callback.getTarget());
        Map<String, List<LifeCycleCallback>> map = null;
        if (callback instanceof PreDestroyCallback)
            map = preDestroyCallbacksMap;
        if (callback instanceof PostConstructCallback)
            map = postConstructCallbacksMap;

        if (map == null)
            throw new IllegalArgumentException ("Unsupported lifecycle callback type: "+callback);
     
        List<LifeCycleCallback> callbacks = map.get(callback.getTargetClassName());
        if (callbacks==null)
        {
            callbacks = new ArrayList<LifeCycleCallback>();
            map.put(callback.getTargetClassName(), callbacks);
        }
       
        //don't add another callback for exactly the same method
        if (!callbacks.contains(callback))
            callbacks.add(callback);
    }

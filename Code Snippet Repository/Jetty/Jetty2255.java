    public void add (Injection injection)
    {
        if ((injection==null) || injection.getTargetClass()==null) 
            return;
        
        if (LOG.isDebugEnabled())
            LOG.debug("Adding injection for class="+(injection.getTargetClass()+ " on a "+(injection.getTarget().getName())));
   
        
        List<Injection> injections = (List<Injection>)_injectionMap.get(injection.getTargetClass().getCanonicalName());
        if (injections==null)
        {
            injections = new ArrayList<Injection>();
            _injectionMap.put(injection.getTargetClass().getCanonicalName(), injections);
        }
        injections.add(injection);
    }

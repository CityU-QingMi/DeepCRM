    public void inject (Object injectable)
    {
        if (injectable==null)
            return;
        
        //Get all injections pertinent to the Object by
        //looking at it's class hierarchy
        Class<?> clazz = injectable.getClass();
        
        while (clazz != null)
        {
            List<Injection> injections = _injectionMap.get(clazz.getCanonicalName());
            if (injections != null)
            {
                for (Injection i : injections)
                    i.inject(injectable);
            }
            
            clazz = clazz.getSuperclass();
        }
    }

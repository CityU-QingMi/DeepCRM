    public Injection getInjection (String jndiName, Class<?> clazz, Field field)
    {
        if (field == null || clazz == null)
            return null;
        
        List<Injection> injections = getInjections(clazz.getCanonicalName());
        if (injections == null)
            return null;
        Iterator<Injection> itor = injections.iterator();
        Injection injection = null;
        while (itor.hasNext() && injection == null)
        {
            Injection i = itor.next();
            if (i.isField() && field.getName().equals(i.getTarget().getName()))
                injection = i;
        }
        
        return injection;
    }

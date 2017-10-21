    public Injection getInjection (String jndiName, Class<?> clazz, Method method, Class<?> paramClass)
    {
        if (clazz == null || method == null || paramClass == null)
            return null;
        
        List<Injection> injections = getInjections(clazz.getCanonicalName());
        if (injections == null)
            return null;
        Iterator<Injection> itor = injections.iterator();
        Injection injection = null;
        while (itor.hasNext() && injection == null)
        {
            Injection i = itor.next();
            if (i.isMethod() && i.getTarget().getName().equals(method.getName()) && paramClass.equals(i.getParamClass()))
                injection = i;
        }
        
        return injection;
    }

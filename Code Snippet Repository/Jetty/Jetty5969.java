    public static void addSystemClasses(Server server,String... pattern )
    {
        if (pattern == null || pattern.length == 0)
            return;
        
        // look for a Server attribute with the list of System classes
        // to apply to every web application. If not present, use our defaults.
        Object o = server.getAttribute(SERVER_SYS_CLASSES);
        if (o instanceof ClasspathPattern)
        {
            ((ClasspathPattern)o).add(pattern);
            return;
        }
        
        String[] system_classes;
        if (o instanceof String[])
            system_classes = (String[])o;
        else
            system_classes = __dftSystemClasses;
        int l = system_classes.length;
        system_classes = Arrays.copyOf(system_classes,l+pattern.length);
        System.arraycopy(pattern,0,system_classes,l,pattern.length);
        server.setAttribute(SERVER_SYS_CLASSES,system_classes);
    }

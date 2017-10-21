    public static void addServerClasses(Server server,String... pattern )
    {
        if (pattern == null || pattern.length == 0)
            return;
        
        // look for a Server attribute with the list of Server classes
        // to apply to every web application. If not present, use our defaults.        
        Object o = server.getAttribute(SERVER_SRV_CLASSES);
        if (o instanceof ClasspathPattern)
        {
            ((ClasspathPattern)o).add(pattern);
            return;
        }
        
        String[] server_classes;
        if (o instanceof String[])
            server_classes = (String[])o;
        else
            server_classes = __dftServerClasses;
        int l = server_classes.length;
        server_classes = Arrays.copyOf(server_classes,l+pattern.length);
        System.arraycopy(pattern,0,server_classes,l,pattern.length);
        server.setAttribute(SERVER_SRV_CLASSES,server_classes);
    }

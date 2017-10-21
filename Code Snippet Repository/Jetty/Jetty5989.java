    protected List<Resource> findClassDirs (WebAppContext context)
    throws Exception
    {
        if (context == null)
            return null;
        
        List<Resource> classDirs = new ArrayList<Resource>();

        Resource webInfClasses = findWebInfClassesDir(context);
        if (webInfClasses != null)
            classDirs.add(webInfClasses);
        List<Resource> extraClassDirs = findExtraClasspathDirs(context);
        if (extraClassDirs != null)
            classDirs.addAll(extraClassDirs);
        
        return classDirs;
    }

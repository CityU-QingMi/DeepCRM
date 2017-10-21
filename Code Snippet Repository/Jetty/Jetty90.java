    protected void parseJarEntry (Set<? extends Handler> handlers, Resource jar, MultiReleaseJarFile.VersionedJarEntry entry) throws Exception
    {
        if (jar == null || entry == null)
            return;

        //skip directories
        if (entry.isDirectory())
            return;

        String name = entry.getName();

        //check file is a valid class file name
        if (isValidClassFileName(name) && isValidClassFilePath(name))
        {
            String shortName =  name.replace('/', '.').substring(0,name.length()-6);
            addParsedClass(shortName, Resource.newResource("jar:"+jar.getURI()+"!/"+entry.getNameInJar()));
            if (LOG.isDebugEnabled())
                LOG.debug("Scanning class from jar {}!/{}", jar, entry);
            try (InputStream is = entry.getInputStream())
            {
                scanClass(handlers, jar, is);
            }
        }
    }

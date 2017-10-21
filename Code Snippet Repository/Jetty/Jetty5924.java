    public Collection<URL>  getTlds (File dir) throws IOException
    {
        if (dir == null || !dir.isDirectory())
            return Collections.emptySet();
        
        HashSet<URL> tlds = new HashSet<URL>();
        
        File[] files = dir.listFiles();
        if (files != null)
        {
            for (File f:files)
            {
                if (f.isDirectory())
                    tlds.addAll(getTlds(f));
                else
                {
                    String name = f.getCanonicalPath();
                    if (name.contains("META-INF") && name.endsWith(".tld"))
                        tlds.add(f.toURI().toURL());
                }
            }
        }
        return tlds;  
    }

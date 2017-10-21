    @Override
    public Set<String> getResourcePaths(String path)
    {
        // Try to get regular resource paths - this will get appropriate paths from any overlaid wars etc
        Set<String> paths = super.getResourcePaths(path);
        
        if (path != null)
        {
            TreeSet<String> allPaths = new TreeSet<String>();
            allPaths.addAll(paths);
            
            //add in the dependency jars as a virtual WEB-INF/lib entry
            if (path.startsWith(WEB_INF_LIB_PREFIX))
            {
                for (String fileName : _webInfJarMap.keySet())
                {
                    // Return all jar files from class path
                    allPaths.add(WEB_INF_LIB_PREFIX + "/" + fileName);
                }
            }
            else if (path.startsWith(WEB_INF_CLASSES_PREFIX))
            {
                int i=0;
               
                while (i < _webInfClasses.size())
                {
                    String newPath = path.replace(WEB_INF_CLASSES_PREFIX, _webInfClasses.get(i).getPath());
                    allPaths.addAll(super.getResourcePaths(newPath));
                    i++;
                }
            }
            return allPaths;
        }
        return paths;
    }

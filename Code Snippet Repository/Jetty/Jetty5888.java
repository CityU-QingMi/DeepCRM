    public boolean match(String name, URL url)
    {
        // Strip class suffix for name matching
        if (name.endsWith(".class"))
            name=name.substring(0,name.length()-6);
        
        // Treat path elements as packages for name matching
        name=name.replace("/",".");

        Boolean byName = _patterns.isIncludedAndNotExcluded(name);
        if (Boolean.FALSE.equals(byName))
            return byName; // Already excluded so no need to check location.
        
        // Try to find a file path for location matching
        Boolean byLocation = null;
        try
        {
            URI jarUri = URIUtil.getJarSource(url.toURI());
            if ("file".equalsIgnoreCase(jarUri.getScheme()))
            {
                byLocation = _locations.isIncludedAndNotExcluded(jarUri);
            }
        }
        catch(Exception e)
        {
            LOG.ignore(e);
        }

        // Combine the tri-state match of both IncludeExclude Sets
        boolean included = byName==Boolean.TRUE || byLocation==Boolean.TRUE
            || (byName==null && !_patterns.hasIncludes() && byLocation==null && !_locations.hasIncludes());
        boolean excluded = byName==Boolean.FALSE || byLocation==Boolean.FALSE;
        return included && !excluded;
    }

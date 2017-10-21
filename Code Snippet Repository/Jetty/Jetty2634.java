    protected Set<String> getOmittedMethods (String omission)
    {
        if (omission == null || !omission.endsWith(OMISSION_SUFFIX))
            return Collections.emptySet();
        
        String[] strings = omission.split("\\.");
        Set<String> methods = new HashSet<String>();
        for (int i=0;i<strings.length-1;i++)
            methods.add(strings[i]);
        return methods;
    }

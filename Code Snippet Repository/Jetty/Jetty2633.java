    protected boolean omissionsExist (String path, Map<String, RoleInfo> methodMappings)
    {
        if (methodMappings == null)
            return false;
        boolean hasOmissions = false;
        for (String m:methodMappings.keySet())
        {
            if (m.endsWith(OMISSION_SUFFIX))
                hasOmissions = true;
        }
        return hasOmissions;
    }

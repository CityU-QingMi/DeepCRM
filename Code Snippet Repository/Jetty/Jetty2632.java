    public Set<String> getPathsWithUncoveredHttpMethods ()
    {
        //if automatically denying uncovered methods, there are no uncovered methods
        if (_denyUncoveredMethods)
            return Collections.emptySet();
        
        Set<String> uncoveredPaths = new HashSet<String>();
        
        for (String path:_constraintMap.keySet())
        {
            Map<String, RoleInfo> methodMappings = _constraintMap.get(path);
            //Each key is either:
            // : an exact method name
            // : * which means that the constraint applies to every method
            // : a name of the form <method>.<method>.<method>.omission, which means it applies to every method EXCEPT those named
            if (methodMappings.get(ALL_METHODS) != null)
                continue; //can't be any uncovered methods for this url path
          
            boolean hasOmissions = omissionsExist(path, methodMappings);
            
            for (String method:methodMappings.keySet())
            {
                if (method.endsWith(OMISSION_SUFFIX))
                {
                    Set<String> omittedMethods = getOmittedMethods(method);
                    for (String m:omittedMethods)
                    {
                        if (!methodMappings.containsKey(m))
                            uncoveredPaths.add(path);
                    }
                }
                else
                {
                    //an exact method name
                    if (!hasOmissions)
                        //a http-method does not have http-method-omission to cover the other method names
                        uncoveredPaths.add(path);
                }
                
            }
        }
        return uncoveredPaths;
    }

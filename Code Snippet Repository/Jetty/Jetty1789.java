    @SuppressWarnings({ "", "" })
    private String[] getGroups (Subject subject)
    {
        //get all the roles of the various types
        String[] roleClassNames = getRoleClassNames();
        Collection<String> groups = new LinkedHashSet<String>();
        try
        {
            for (String roleClassName : roleClassNames)
            {
                Class load_class = Thread.currentThread().getContextClassLoader().loadClass(roleClassName);
                Set<Principal> rolesForType = subject.getPrincipals(load_class);
                for (Principal principal : rolesForType)
                {
                    groups.add(principal.getName());
                }
            }

            return groups.toArray(new String[groups.size()]);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

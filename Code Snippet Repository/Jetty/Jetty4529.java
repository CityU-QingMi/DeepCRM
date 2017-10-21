    private Set<Module> getAvailableProviders(String name)
    {
        // Get all available providers 
        Set<Module> providers = _provided.get(name);
        StartLog.debug("Providers of %s are %s",name,providers);
        if (providers==null || providers.isEmpty())
            return Collections.emptySet();

        providers = new HashSet<>(providers);
        
        // find all currently provided names by other modules
        Set<String> provided = new HashSet<>();
        for (Module m : _modules)
        {
            if (m.isEnabled())
            {
                provided.add(m.getName());
                provided.addAll(m.getProvides());
            }
        }
        
        // Remove any that cannot be selected
        for (Iterator<Module> i = providers.iterator(); i.hasNext();)
        {
            Module provider = i.next();
            if (!provider.isEnabled())
            {    
                for (String p : provider.getProvides())
                {
                    if (provided.contains(p))
                    {
                        StartLog.debug("Removing provider %s because %s already enabled",provider,p);
                        i.remove();
                        break;
                    }
                }
            }
        }

        StartLog.debug("Available providers of %s are %s",name,providers);
        return providers;
    }

    public void checkEnabledModules()
    {
        StringBuilder unsatisfied=new StringBuilder();
        _modules.stream().filter(Module::isEnabled).forEach(m->
        {
            // Check dependencies
            m.getDepends().forEach(d->
            {
                Set<Module> providers = getAvailableProviders(d);
                if (providers.stream().filter(Module::isEnabled).count()==0)
                { 
                    if (unsatisfied.length()>0)
                        unsatisfied.append(',');
                    unsatisfied.append(m.getName());
                    StartLog.error("Module %s requires a module providing %s from one of %s%n",m.getName(),d,providers);
                }
            });
        });
        
        if (unsatisfied.length()>0)
            throw new UsageException(-1,"Unsatisfied module dependencies: "+unsatisfied);
    }

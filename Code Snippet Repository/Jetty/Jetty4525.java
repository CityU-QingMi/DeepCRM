    private Module registerModule(Path file)
    {
        if (!FS.canReadFile(file))
        {
            throw new IllegalStateException("Cannot read file: " + file);
        }
        String shortName = _baseHome.toShortForm(file);
        try
        {
            StartLog.debug("Registering Module: %s",shortName);
            Module module = new Module(_baseHome,file);
            _modules.add(module);
            _names.put(module.getName(),module);
            module.getProvides().forEach(n->{
                _provided.computeIfAbsent(n,k->new HashSet<Module>()).add(module);
            });
            
            return module;
        }
        catch (Error|RuntimeException t)
        {
            throw t;
        }
        catch (Throwable t)
        {
            throw new IllegalStateException("Unable to register module: " + shortName,t);
        }
    }

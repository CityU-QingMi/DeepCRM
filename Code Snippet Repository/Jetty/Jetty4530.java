    public Module get(String name)
    {
        Module module = _names.get(name);
        if (module==null)
        {
            String reason = _deprecated.getProperty(name);
            if (reason!=null)
                StartLog.warn("Module %s is no longer available: %s",name,reason);
        }
        return module;
    }

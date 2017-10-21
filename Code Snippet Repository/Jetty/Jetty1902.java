    public NamingContext(Hashtable<String,Object> env,
                         String name,
                         NamingContext parent,
                         NameParser parser)
    {
        if (env != null)
        {
            _env.putAll(env);
            // look for deep binding support in _env
            Object support = _env.get(DEEP_BINDING);
            if (support == null)
                _supportDeepBinding = false;
            else
                _supportDeepBinding = support != null?Boolean.parseBoolean(support.toString()):false;
        }
        else
        {
            // no env?  likely this is a root context (java or local) that
            // was created without an _env.  Look for a system property.
            String value = System.getProperty(DEEP_BINDING,"false");
            _supportDeepBinding = Boolean.parseBoolean(value);
            // put what we discovered into the _env for later sub-contexts
            // to utilize.
            _env.put(DEEP_BINDING,_supportDeepBinding);
        }
        _name = name;
        _parent = parent;
        _parser = parser;
        if(__log.isDebugEnabled())
            __log.debug("supportDeepBinding={}",_supportDeepBinding);
    }

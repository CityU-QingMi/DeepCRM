    public StdErrLog(String name, Properties props)
    {
        if (props!=null && props!=Log.__props)
            Log.__props.putAll(props);
        _name = name == null?"":name;
        _abbrevname = condensePackageString(this._name);
        _level = getLoggingLevel(Log.__props,this._name);
        _configuredLevel = _level;

        try
        {
            String source = getLoggingProperty(Log.__props,_name,"SOURCE");
            _source = source==null?__source:Boolean.parseBoolean(source);
        }
        catch (AccessControlException ace)
        {
            _source = __source;
        }

        try
        {
            // allow stacktrace display to be controlled by properties as well
            String stacks = getLoggingProperty(Log.__props,_name,"STACKS");
            _hideStacks = stacks==null?false:!Boolean.parseBoolean(stacks);
        }
        catch (AccessControlException ignore)
        {
            /* ignore */
        }        
    }

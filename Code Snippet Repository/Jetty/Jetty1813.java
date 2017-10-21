    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options)
    {
        super.initialize(subject, callbackHandler, sharedState, options);

        _hostname = (String)options.get("hostname");
        _port = Integer.parseInt((String)options.get("port"));
        _contextFactory = (String)options.get("contextFactory");
        _bindDn = (String)options.get("bindDn");
        _bindPassword = (String)options.get("bindPassword");
        _authenticationMethod = (String)options.get("authenticationMethod");

        _userBaseDn = (String)options.get("userBaseDn");

        _roleBaseDn = (String)options.get("roleBaseDn");

        if (options.containsKey("forceBindingLogin"))
        {
            _forceBindingLogin = Boolean.parseBoolean((String)options.get("forceBindingLogin"));
        }

        if (options.containsKey("useLdaps"))
        {
            _useLdaps = Boolean.parseBoolean((String)options.get("useLdaps"));
        }

        _userObjectClass = getOption(options, "userObjectClass", _userObjectClass);
        _userRdnAttribute = getOption(options, "userRdnAttribute", _userRdnAttribute);
        _userIdAttribute = getOption(options, "userIdAttribute", _userIdAttribute);
        _userPasswordAttribute = getOption(options, "userPasswordAttribute", _userPasswordAttribute);
        _roleObjectClass = getOption(options, "roleObjectClass", _roleObjectClass);
        _roleMemberAttribute = getOption(options, "roleMemberAttribute", _roleMemberAttribute);
        _roleNameAttribute = getOption(options, "roleNameAttribute", _roleNameAttribute);
        _debug = Boolean.parseBoolean(String.valueOf(getOption(options, "debug", Boolean.toString(_debug))));

        try
        {
            _rootContext = new InitialDirContext(getEnvironment());
        }
        catch (NamingException ex)
        {
            throw new IllegalStateException("Unable to establish root context", ex);
        }
    }

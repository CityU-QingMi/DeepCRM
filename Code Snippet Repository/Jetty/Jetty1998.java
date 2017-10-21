    public static void configureLoginServices (Server server, LoginService[] loginServices)
    {
        if (server == null)
            throw new IllegalArgumentException ("Server is null");

        if (loginServices != null)
        {
            for (LoginService loginService:loginServices)
            {
                PluginLog.getLog().debug(loginService.getClass().getName() + ": "+ loginService.toString());
                server.addBean(loginService);
            }
        }
    }

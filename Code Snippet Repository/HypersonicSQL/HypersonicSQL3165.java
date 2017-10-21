    public static ServerProperties newDefaultProperties(int protocol) {

        ServerProperties p = new ServerProperties(protocol);

        p.setProperty(ServerProperties.sc_key_autorestart_server,
                      SC_DEFAULT_SERVER_AUTORESTART);
        p.setProperty(ServerProperties.sc_key_address, SC_DEFAULT_ADDRESS);
        p.setProperty(ServerProperties.sc_key_no_system_exit,
                      SC_DEFAULT_NO_SYSTEM_EXIT);
        p.setProperty(ServerProperties.sc_key_max_databases,
                      SC_DEFAULT_MAX_DATABASES);
        p.setProperty(ServerProperties.sc_key_silent, SC_DEFAULT_SILENT);
        p.setProperty(ServerProperties.sc_key_tls, SC_DEFAULT_TLS);
        p.setProperty(ServerProperties.sc_key_trace, SC_DEFAULT_TRACE);
        p.setProperty(ServerProperties.sc_key_web_default_page,
                      SC_DEFAULT_WEB_PAGE);
        p.setProperty(ServerProperties.sc_key_web_root, SC_DEFAULT_WEB_ROOT);

        // Purposefully do not set a default Port because the default is
        // derived from TLS, which is runtime-configurable.
        // Things work very well if we leave it unset here and use the
        // getDefaultPort() method above to get the correct value.
        return p;
    }

    public void setProperties(HsqlProperties props)
    throws IOException, ServerAcl.AclFormatException {

        if (!isNotRunning()) {
            checkRunning(false);
        }

        if (props != null) {
            props.validate();

            String[] errors = props.getErrorKeys();

            if (errors.length > 0) {
                throw Error.error(ErrorCode.SERVER_NO_DATABASE, errors[0]);
            }

            serverProperties.addProperties(props);
        }

        maxConnections = serverProperties.getIntegerProperty(
            ServerProperties.sc_key_max_connections, 16);

        JavaSystem.setLogToSystem(isTrace());

        isSilent =
            serverProperties.isPropertyTrue(ServerProperties.sc_key_silent);
        isRemoteOpen = serverProperties.isPropertyTrue(
            ServerProperties.sc_key_remote_open_db);
        isDaemon =
            serverProperties.isPropertyTrue(ServerProperties.sc_key_daemon);

        String aclFilepath =
            serverProperties.getProperty(ServerProperties.sc_key_acl);

        if (aclFilepath != null) {
            acl = new ServerAcl(new File(aclFilepath));

            if (logWriter != null && !isSilent) {
                acl.setPrintWriter(logWriter);
            }
        }
    }

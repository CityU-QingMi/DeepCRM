    public String execute() {

        if (WebloggerFactory.isBootstrapped()) {
            return SUCCESS;
        }

        if (WebloggerStartup.getDatabaseProviderException() != null) {
            StartupException se = WebloggerStartup.getDatabaseProviderException();
            if (se.getRootCause() != null) {
                rootCauseException = se.getRootCause();
            } else {
                rootCauseException = se;
            }
            messages = se.getStartupLog();

            log.debug("Forwarding to database error page");
            setPageTitle("installer.error.connection.pageTitle");
            return DATABASE_ERROR;
        }

        if (WebloggerStartup.isDatabaseCreationRequired()) {
            log.debug("Forwarding to database table creation page");
            setPageTitle("installer.database.creation.pageTitle");
            return CREATE_DATABASE;
        }
        if (WebloggerStartup.isDatabaseUpgradeRequired()) {
            log.debug("Forwarding to database table upgrade page");
            setPageTitle("installer.database.upgrade.pageTitle");
            return UPGRADE_DATABASE;
        }

        setPageTitle("installer.error.unknown.pageTitle");
        rootCauseException = new Exception("UNKNOWN ERROR");
        rootCauseException.fillInStackTrace();
        return BOOTSTRAP;
    }

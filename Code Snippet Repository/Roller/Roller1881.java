    public static void prepare() throws StartupException {
        
        // setup database provider
        try {
            dbProvider = new DatabaseProvider();
        } catch(StartupException ex) {
            dbProviderException = ex;
            throw ex;
        }
        
        // setup mail provider, if configured
        try {
            mailProvider = new MailProvider();
        } catch(StartupException ex) {
            LOG.warn("Failed to setup mail provider, continuing anyways.\n"
                    + "Reason: " + ex.getMessage());
        }
        
        // now we need to deal with database install/upgrade logic
        if("manual".equals(WebloggerConfig.getProperty("installation.type"))) {
            
            // if we are doing manual install then all that is needed is the
            // app handled database upgrade work, not the db scripts
            DatabaseInstaller dbInstaller = getDatabaseInstaller();
            if (dbInstaller.isUpgradeRequired()) {
                dbInstaller.upgradeDatabase(false);
            }
            
            prepared = true;
            
        } else {
            
            // we are in auto install mode, so see if there is any work to do
            DatabaseInstaller dbInstaller = getDatabaseInstaller();
            if (!dbInstaller.isCreationRequired() &&
                    !dbInstaller.isUpgradeRequired()) {
                prepared = true;
            }
        }
        
    }

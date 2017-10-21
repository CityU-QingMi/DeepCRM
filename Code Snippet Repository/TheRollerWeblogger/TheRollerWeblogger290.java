    public static List<String> upgradeDatabase(boolean runScripts) 
            throws StartupException {
        
        DatabaseInstaller installer = getDatabaseInstaller();
        try {
            installer.upgradeDatabase(true);
            
            // any time we've successfully upgraded a db we are prepared
            prepared = true;
            
        } catch (StartupException se) {
            throw new StartupException(se.getMessage(), se.getRootCause(), installer.getMessages());
        }
        
        return installer.getMessages();
    }

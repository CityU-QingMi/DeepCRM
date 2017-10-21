    public static List<String> createDatabase() throws StartupException {
        
        DatabaseInstaller installer = getDatabaseInstaller();
        try {
            installer.createDatabase();
            
            // any time we've successfully installed a db we are prepared
            prepared = true;
            
        } catch (StartupException se) {
            throw new StartupException(se.getMessage(), se.getRootCause(), installer.getMessages());
        }
        
        return installer.getMessages();
    }

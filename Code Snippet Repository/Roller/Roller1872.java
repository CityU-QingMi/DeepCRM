	private void upgradeTo510(Connection con, boolean runScripts) throws StartupException {
        
        // first we need to run upgrade scripts 
        SQLScriptRunner runner = null;
        try {    
            if (runScripts) {
                String handle = getDatabaseHandle(con);
                String scriptPath = handle + "/500-to-510-migration.sql";
                successMessage("Running database upgrade script: "+scriptPath);                
                runner = new SQLScriptRunner(scripts.getDatabaseScript(scriptPath));
                runner.runScript(con, true);
                messages.addAll(runner.getMessages());
            }
        } catch(Exception ex) {
            log.error("ERROR running 510 database upgrade script", ex);
            if (runner != null) {
                messages.addAll(runner.getMessages());
            }
            
            errorMessage("Problem upgrading database to version 510", ex);
            throw new StartupException("Problem upgrading database to version 510", ex);
        }        
	}

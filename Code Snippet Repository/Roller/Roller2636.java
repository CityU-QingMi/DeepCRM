    public void testParseOnly() throws Exception {        
        DatabaseProvider dbp = WebloggerStartup.getDatabaseProvider();
        Connection con = dbp.getConnection(); 
        
        // normaly tests run against Derby
        String databaseProductName = con.getMetaData().getDatabaseProductName();
        String dbname = "derby";
        if (databaseProductName.toLowerCase().indexOf("mysql") > 0) {
            // but some folks test against MySQL
            dbname = "mysql";
        }
        
        String scriptPath = System.getProperty("project.build.directory")
                + "/test-classes/WEB-INF/dbscripts/dummydb/createdb-"+dbname+".sql";
        SQLScriptRunner runner = new SQLScriptRunner(scriptPath);
        assertTrue(runner != null);
        assertTrue(runner.getCommandCount() == 5);        
    }    

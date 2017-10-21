    public void testSimpleRun() throws Exception {
        DatabaseProvider dbp = WebloggerStartup.getDatabaseProvider();
        Connection con = dbp.getConnection(); 

        // normaly tests run against Derby
        String databaseProductName = con.getMetaData().getDatabaseProductName();
        String dbname = "derby";
        if (databaseProductName.toLowerCase().indexOf("mysql") > 0) {
            // but some folks test against MySQL
            dbname = "mysql";
        }
        
        // run script to create tables
        SQLScriptRunner create = 
            new SQLScriptRunner(System.getProperty("project.build.directory")
                    + "/test-classes/WEB-INF/dbscripts/dummydb/createdb-"+dbname+".sql");
        create.runScript(con, true);
        
        // check to ensure tables were created
        assertTrue(tableExists(con, "testrolleruser"));
        assertTrue(tableExists(con, "testuserrole"));
        
        // drop tables
        SQLScriptRunner drop = 
            new SQLScriptRunner(System.getProperty("project.build.directory") + "/test-classes/WEB-INF/dbscripts/dummydb/droptables.sql");
        drop.runScript(con, false);

        // check to ensure tables were dropped
        assertFalse(tableExists(con, "testrolleruser"));
        assertFalse(tableExists(con, "testuserrole"));
    }

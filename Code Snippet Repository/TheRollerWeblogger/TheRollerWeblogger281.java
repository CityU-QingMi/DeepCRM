    public void createDatabase() throws StartupException {
        
        log.info("Creating Roller Weblogger database tables.");
        
        Connection con = null;
        SQLScriptRunner create = null;
        try {
            con = db.getConnection();
            String handle = getDatabaseHandle(con);
            create = new SQLScriptRunner(scripts.getDatabaseScript(handle + "/createdb.sql"));
            create.runScript(con, true);
            messages.addAll(create.getMessages());
            
            setDatabaseVersion(con, version);
            
        } catch (SQLException sqle) {
            log.error("ERROR running SQL in database creation script", sqle);
            if (create != null) {
                messages.addAll(create.getMessages());
            }
            errorMessage("ERROR running SQL in database creation script");
            throw new StartupException("Error running sql script", sqle); 
            
        } catch (Exception ioe) {
            log.error("ERROR running database creation script", ioe);
            if (create != null) {
                messages.addAll(create.getMessages());
            }
            errorMessage("ERROR reading/parsing database creation script");
            throw new StartupException("Error running SQL script", ioe);

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ignored) {}
        }
    }

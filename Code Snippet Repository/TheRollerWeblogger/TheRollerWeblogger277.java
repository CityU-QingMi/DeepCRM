    private void setDatabaseVersion(Connection con, int version)
            throws StartupException {
        
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into roller_properties "+
                    "values('"+DBVERSION_PROP+"', '"+version+"')");
            
            log.debug("Set database verstion to "+version);
        } catch(SQLException se) {
            throw new StartupException("Error setting database version.", se);
        }
    }

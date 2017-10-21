    private void updateDatabaseVersion(Connection con, int version)
            throws StartupException {
        
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update roller_properties "+
                    "set value = '"+version+"'"+
                    "where name = '"+DBVERSION_PROP+"'");
            
            log.debug("Updated database verstion to "+version);
        } catch(SQLException se) {
            throw new StartupException("Error setting database version.", se);
        } 
    }

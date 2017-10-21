    private int getDatabaseVersion() throws StartupException {
        int dbversion = -1;
        
        // get the current db version
        Connection con = null;
        try {
            con = db.getConnection();
            Statement stmt = con.createStatement();
            
            // just check in the roller_properties table
            ResultSet rs = stmt.executeQuery(
                    "select value from roller_properties where name = '"+DBVERSION_PROP+"'");
            
            if(rs.next()) {
                dbversion = Integer.parseInt(rs.getString(1));
                
            } else {
                // tough to know if this is an upgrade with no db version :/
                // however, if roller_properties is not empty then we at least
                // we have someone upgrading from 1.2.x
                rs = stmt.executeQuery("select count(*) from roller_properties");
                if (rs.next() && rs.getInt(1) > 0) {
                    dbversion = 120;
                }
            }
            
        } catch(Exception e) {
            // that's strange ... hopefully we didn't need to upgrade
            log.error("Couldn't lookup current database version", e);           
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ignored) {}
        }       
        return dbversion;
    }

    public boolean isCreationRequired() {
        Connection con = null;
        try {            
            con = db.getConnection();
            
            // just check for a couple key Roller tables
            // roller_user table called rolleruser before Roller 5.1
            if (tableExists(con, "userrole") && (tableExists(con, "roller_user") || tableExists(con, "rolleruser"))) {
                return false;
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error checking for tables", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ignored) {}
        }
        
        return true;
    }

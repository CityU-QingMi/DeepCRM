    private static void revokeAdmin(Connection con, String userName) throws Exception
    {
        // Find userid of specified user
        String userid;
        PreparedStatement userQuery = con.prepareStatement(
           "select id from roller_user where username=?");
        userQuery.setString(1, userName);
        ResultSet userRS = userQuery.executeQuery();
        if (!userRS.next()) 
        {
            System.err.println("ERROR: username not found in database");
            return;
        }
        else 
        {
            userid = userRS.getString(1);
        }
        
        // Delete user's admin entries from userrole table
        PreparedStatement roleDelete = con.prepareStatement(
           "delete from userrole where userid=? and rolename='admin'");
        roleDelete.setString(1, userid);
        roleDelete.executeUpdate();
    }

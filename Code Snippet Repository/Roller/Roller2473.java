    private static void grantAdmin(Connection con, String userName) throws Exception
    {
        // Find userid of specified user
        String userid;
        PreparedStatement userQuery = con.prepareStatement(
           "select id from roller_user where username=?");
        userQuery.setString(1, userName);
        ResultSet userRS = userQuery.executeQuery();
        if (!userRS.next()) {
            System.err.println("ERROR: username not found in database");
            return;
        } else {
            userid = userRS.getString(1);
        }
        
        // Is user already an admin?
        PreparedStatement roleQuery = con.prepareStatement(
           "select username from userrole where username=? and rolename='admin'");
        roleQuery.setString(1, userName);
        ResultSet roleRS = roleQuery.executeQuery();
        if (!roleRS.next()) {
            // User not admin; add admin role
            PreparedStatement adminInsert = con.prepareStatement(
               "insert into userrole (id,rolename,username,userid) values (?,?,?,?)");
            adminInsert.setString(1, userName);
            adminInsert.setString(2, "admin");
            adminInsert.setString(3, userName);
            adminInsert.setString(4, userid);
            adminInsert.executeUpdate();
            System.out.println("User granted admin role");
        } else {
            System.out.println("User was already an admin");
        }
    }

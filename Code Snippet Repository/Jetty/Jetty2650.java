    public String[] loadRoleInfo (UserPrincipal user)
    {
        JDBCUserPrincipal jdbcUser = (JDBCUserPrincipal)user;
        
        try
        {
            if (null == _con) 
                connectDatabase();

            if (null == _con) 
                throw new SQLException("Can't connect to database");
            
            
            List<String> roles = new ArrayList<String>();

            try (PreparedStatement stat2 = _con.prepareStatement(_roleSql))
            {
                stat2.setInt(1, jdbcUser.getUserKey());
                try (ResultSet rs2 = stat2.executeQuery())
                {
                    while (rs2.next())
                        roles.add(rs2.getString(_roleTableRoleField));
                    return roles.toArray(new String[roles.size()]);
                }
            }
        }
        catch (SQLException e)
        {
            LOG.warn("UserRealm " + getName() + " could not load user information from database", e);
            closeConnection();
        }
        
        return null;
    }

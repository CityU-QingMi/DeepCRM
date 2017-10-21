    public UserPrincipal loadUserInfo (String username)
    {
        try
        {
            if (null == _con) 
                connectDatabase();

            if (null == _con) 
                throw new SQLException("Can't connect to database");

            try (PreparedStatement stat1 = _con.prepareStatement(_userSql))
            {
                stat1.setObject(1, username);
                try (ResultSet rs1 = stat1.executeQuery())
                {
                    if (rs1.next())
                    {
                        int key = rs1.getInt(_userTableKey);
                        String credentials = rs1.getString(_userTablePasswordField);

                        return new JDBCUserPrincipal (username, Credential.getCredential(credentials), key);
                    }
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

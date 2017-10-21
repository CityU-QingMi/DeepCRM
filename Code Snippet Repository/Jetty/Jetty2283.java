    public UserPrincipal loadUserInfo (String username)
    {
        try
        {
            try (Connection connection = getConnection();
                    PreparedStatement statement1 = connection.prepareStatement(_userSql))
            {
                statement1.setObject(1, username);
                try (ResultSet rs1 = statement1.executeQuery())
                {
                    if (rs1.next())
                    {
                        int key = rs1.getInt(_userTableKey);
                        String credentials = rs1.getString(_userTablePasswordField);
                        
                        return new DBUserPrincipal(username, Credential.getCredential(credentials), key);
                    }
                }
            }
        }
        catch (NamingException e)
        {
            LOG.warn("No datasource for "+_jndiName, e);
        }
        catch (SQLException e)
        {
            LOG.warn("Problem loading user info for "+username, e);
        }
        return null;
    }

    public String[] loadRoleInfo (UserPrincipal user)
    {
        DBUserPrincipal dbuser = (DBUserPrincipal)user;

        try
        {
            try (Connection connection = getConnection();
                    PreparedStatement statement2 = connection.prepareStatement(_roleSql))
            {

                List<String> roles = new ArrayList<String>();

                statement2.setInt(1, dbuser.getKey());
                try (ResultSet rs2 = statement2.executeQuery())
                {
                    while (rs2.next())
                    {
                        roles.add(rs2.getString(_roleTableRoleField));
                    }
                    
                    return roles.toArray(new String[roles.size()]);
                }
            }
        }
        catch (NamingException e)
        {
            LOG.warn("No datasource for "+_jndiName, e);
        }
        catch (SQLException e)
        {
            LOG.warn("Problem loading user info for "+user.getName(), e);
        }
        return null;
    }

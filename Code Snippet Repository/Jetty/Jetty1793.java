    public List<String>  getRoles (String userName)
    throws Exception
    {
        List<String> roles = new ArrayList<String>();
        
        try (Connection connection = getConnection())
        {
            //query for role names

            try (PreparedStatement statement = connection.prepareStatement (rolesQuery))
            {
                statement.setString (1, userName);
                try (ResultSet results = statement.executeQuery())
                {
                    while (results.next())
                    {
                        String roleName = results.getString (1);
                        roles.add (roleName);
                    }
                }
            }
          
        }

        return roles;
    }

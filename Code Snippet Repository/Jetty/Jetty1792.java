    public UserInfo getUserInfo (String userName)
        throws Exception
    {
        try (Connection connection = getConnection())
        {

            //query for credential
            String dbCredential = null;
            try (PreparedStatement statement = connection.prepareStatement (userQuery))
            {
                statement.setString (1, userName);
                try (ResultSet results = statement.executeQuery())
                {
                    if (results.next())
                    {
                        dbCredential = results.getString(1);
                    }
                }
            }

            if (dbCredential==null)
            {
                return null;
            }

          

            return new JDBCUserInfo (userName, Credential.getCredential(dbCredential));
        }
    }

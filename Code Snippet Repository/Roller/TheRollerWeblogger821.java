    private static void encryptionOn(
                    Connection con, String algorithm) throws Exception
    {
        PreparedStatement userQuery = con
        	.prepareStatement("select username,passphrase from roller_user");
        PreparedStatement userUpdate = con
        	.prepareStatement("update roller_user set passphrase=? where username=?");

        Properties props = new Properties();
        ResultSet users = userQuery.executeQuery();
        while (users.next())
        {
            String username = users.getString(1);
            String passphrase = users.getString(2);
            props.put(username, passphrase);
        }
        Enumeration usernames = props.keys();
        while (usernames.hasMoreElements())
        {
            String username = (String)usernames.nextElement();
            String passphrase = (String)props.get(username);
            userUpdate.clearParameters();
            userUpdate.setString(1, Utilities.encodePassword(passphrase, algorithm));
            userUpdate.setString(2, username);
            userUpdate.executeUpdate();
            System.out.println("Encrypted password for user: " + username);
        }
    }

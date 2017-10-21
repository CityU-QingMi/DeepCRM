    private static void encryptionOff(
                    Connection con, String fileName) throws Exception
    {
        PreparedStatement userUpdate = con
			.prepareStatement("update roller_user set passphrase=? where username=?");

        Properties props = new Properties();
        props.load(new FileInputStream(fileName));
        Enumeration usernames = props.keys();
        while (usernames.hasMoreElements())
        {
            String username = (String)usernames.nextElement();
            String password = (String)props.get(username);
            userUpdate.clearParameters();
            userUpdate.setString(1, password);
            userUpdate.setString(2, username);
            userUpdate.executeUpdate();
        }
    }

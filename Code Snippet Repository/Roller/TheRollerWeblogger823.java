    private static void resetPassword(
                    Connection con, String username, String password, String algorithm) 
    	    throws Exception
    {
		PreparedStatement userUpdate =
            con.prepareStatement("update roller_user set passphrase=? where username=?");
		
		String newPassword = Utilities.encodePassword(password, algorithm);
		userUpdate.setString(1, newPassword);
		userUpdate.setString(2, username);
		userUpdate.executeUpdate();
    }   

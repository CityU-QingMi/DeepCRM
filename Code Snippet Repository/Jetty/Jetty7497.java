     protected void changePassword (String user, String newpwd) throws Exception
     {
         Loader.loadClass("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
         try (Connection connection = DriverManager.getConnection(DatabaseLoginServiceTestServer.__dbURL, "", "");
              Statement stmt = connection.createStatement())
         {
             connection.setAutoCommit(true);
             stmt.executeUpdate("update users set pwd='"+newpwd+"' where username='"+user+"'");
         }
         
     }

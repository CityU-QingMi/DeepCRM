   @Override
   public boolean exists(String id)
   throws Exception
   {
       try (Connection connection = _dbAdaptor.getConnection())
       {
           connection.setAutoCommit(true);

           //non-expired session exists?
           try (PreparedStatement checkSessionExists = _sessionTableSchema.getCheckSessionExistsStatement(connection, _context))
           {
               checkSessionExists.setString(1, id);
               try (ResultSet result = checkSessionExists.executeQuery())
               {        
                   if (!result.next())
                   {
                       return false; //no such session
                   }
                   else
                   {
                       long expiry = result.getLong(_sessionTableSchema.getExpiryTimeColumn());
                       if (expiry <= 0) //never expires
                           return true;
                       else
                           return (expiry > System.currentTimeMillis()); //hasn't already expired
                   }
               }
           }
       }
   }
